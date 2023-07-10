package discord_bot.command_player.Chat.Commands.CommandsListeners;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.CommandsTypes.QueueCommand;
import discord_bot.command_player.Music.AudioTrackScheduler;
import reactor.core.publisher.Mono;

@Service
public class QueueCommandListener implements CommandListener<QueueCommand> {

    @Override
    public Class<QueueCommand> getCommandType() {
        return QueueCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {
        AudioTrackScheduler songsListSheduler = BotConfiguration.getGuildAudioManager().getScheduler();
        List<AudioTrack> songsList = songsListSheduler.getQueue();
        AudioTrack currentTrack = songsListSheduler.getCurrentTrack();

        String tracksInfo = "There aren't any songs in the queue";

        if (currentTrack != null) {
            tracksInfo = "Track #1: " + currentTrack.getInfo().title + " by: " + currentTrack.getInfo().author + "\n";
        }

        if (songsList.size() == 0) {
            return MessagePrinter.printMessage(message, tracksInfo);
        }

        int k = 2;
        for (AudioTrack track : songsList) {
            {
                tracksInfo += "Track #" + k + ": " + track.getInfo().title + " by: " + track.getInfo().author
                        + "\n";
                k++;
            }
        }
        return MessagePrinter.printMessage(message, tracksInfo);
    }
}
