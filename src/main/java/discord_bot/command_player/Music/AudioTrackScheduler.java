package discord_bot.command_player.Music;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sedmelluq.discord.lavaplayer.player.AudioPlayer;
import com.sedmelluq.discord.lavaplayer.player.event.AudioEventAdapter;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;
import com.sedmelluq.discord.lavaplayer.track.AudioTrackEndReason;

import discord4j.core.object.entity.channel.MessageChannel;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.MessagePrinter;
import reactor.core.publisher.Mono;

public class AudioTrackScheduler extends AudioEventAdapter {

  private AudioTrack currentTrack;
  private final List<AudioTrack> queue;
  private final AudioPlayer player;

  public AudioTrackScheduler(AudioPlayer player) {
    // The queue may be modifed by different threads so guarantee memory safety
    // This does not, however, remove several race conditions currently present
    currentTrack = null;
    queue = Collections.synchronizedList(new LinkedList<>());
    this.player = player;
  }

  public void setCurrentTrack(AudioTrack track) {
    this.currentTrack = track;
  }

  public AudioTrack getCurrentTrack() {
    return currentTrack;
  }

  public List<AudioTrack> getQueue() {
    return queue;
  }

  public void removeAllFromQueue() {
    player.stopTrack();
    setCurrentTrack(null);
    queue.clear();
  }

  public boolean play(AudioTrack track) {
    return play(track, false);
  }

  public boolean play(AudioTrack track, boolean force) {
    if (track == currentTrack)
      player.stopTrack();
    boolean playing = player.startTrack(track, !force);

    if (!playing) {
      queue.add(track);
      return false;
    }
    setCurrentTrack(track);
    return playing;
  }

  public SkipDetails skip() {
    if (!queue.isEmpty()) {
      AudioTrack nextTrack = queue.remove(0);
      return new SkipDetails(play(nextTrack, true), nextTrack);
    } else {
      player.stopTrack();
      AudioTrack skippedTrack = currentTrack;
      setCurrentTrack(null);
      return new SkipDetails(true, skippedTrack);
    }
  }

  @Override
  public void onTrackEnd(AudioPlayer player, AudioTrack track, AudioTrackEndReason endReason) {
    // Advance the player if the track completed naturally (FINISHED) or if the
    // track cannot play (LOAD_FAILED)
    if (endReason.mayStartNext) {
      skip();
    }
  }

  @Override
  public void onTrackStart(AudioPlayer player, AudioTrack track) {
    MessageChannel channel = BotConfiguration.getGuildAudioManager().getMessageChannel();
    if (channel != null)
      Mono.just(0).then(MessagePrinter.printMessageInChannel(channel,
          "Now playing: **" + track.getInfo().title + "** by: **" + track.getInfo().author + "**")).block();
  }
}