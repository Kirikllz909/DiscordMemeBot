package discord_bot.command_player.Music;

import com.sedmelluq.discord.lavaplayer.player.AudioLoadResultHandler;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException;
import com.sedmelluq.discord.lavaplayer.tools.FriendlyException.Severity;
import com.sedmelluq.discord.lavaplayer.track.AudioPlaylist;
import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import discord_bot.command_player.BotConfiguration;

public class TrackLoadingHandler implements AudioLoadResultHandler {

    @Override
    public void trackLoaded(AudioTrack track) {
        BotConfiguration.getGuildAudioManager().getScheduler().play(track);
    }

    @Override
    public void playlistLoaded(AudioPlaylist playlist) {
        for (AudioTrack track : playlist.getTracks()) {
            BotConfiguration.getGuildAudioManager().getScheduler().play(track);
        }
    }

    @Override
    public void noMatches() throws FriendlyException {
        throw new FriendlyException("No matches for track", Severity.COMMON,
                new Exception("User tried to find track but it doesn't have matches"));
    }

    @Override
    public void loadFailed(FriendlyException exception) throws FriendlyException {
        throw exception;
    }
}
