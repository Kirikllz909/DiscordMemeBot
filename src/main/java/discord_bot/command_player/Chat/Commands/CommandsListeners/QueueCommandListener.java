package discord_bot.command_player.Chat.Commands.CommandsListeners;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.QueueCommand;
import reactor.core.publisher.Mono;

@Service
public class QueueCommandListener implements CommandListener<QueueCommand> {

    @Override
    public Class<QueueCommand> getCommandType() {
        return QueueCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {
        List<AudioTrack> songsList = BotConfiguration.getGuildAudioManager().getScheduler().getQueue();
        if (songsList == null) {
            return MessagePrinter.printMessage(message, "There aren't any songs in the queue");
        }
        Mono<Void> trackQueueProcess = Mono.just(null).then();
        for (AudioTrack track : songsList) {
            trackQueueProcess.then(MessagePrinter.printMessage(message,
                    "Currently playing: " + track.getInfo().title + "\t Link: " + track.getInfo().uri));
        }
        return trackQueueProcess;
    }
}
