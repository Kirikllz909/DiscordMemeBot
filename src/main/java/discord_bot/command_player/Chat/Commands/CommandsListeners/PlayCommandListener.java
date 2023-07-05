package discord_bot.command_player.Chat.Commands.CommandsListeners;

import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.PlayCommand;
import discord_bot.command_player.Music.PlayerManager;
import discord_bot.command_player.Music.TrackLoadingHandler;
import reactor.core.publisher.Mono;

//TODO: check if bot is already connected to voice channel. If not connect it
//TODO: after !play command return message track added to playlist
//TODO: create a command !queue to get a queue
//TODO: create a now playing track embbeded msg
//TODO: create a leaving channel after long delay and there is no tracks for like 1 minute
//TODO: create a skip command

@Service
public class PlayCommandListener implements CommandListener<PlayCommand> {

    private boolean success = true;

    @Override
    public Class<PlayCommand> getCommandType() {
        return PlayCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {
        if (args.length == 0) {
            return MessagePrinter.printMessage(message, "There is no link to the song");
        }
        JoinCommandListener joinCommandListener = new JoinCommandListener();
        Mono<Void> afterJoining = joinCommandListener.processCommand(message, args);
        Future<Void> futureForLoading = PlayerManager.PLAYER_MANAGER.loadItem(
                args[0],
                new TrackLoadingHandler());

        return afterJoining.then(Mono.fromCallable(() -> {
            try {
                return futureForLoading.get();
            } catch (Exception e) {
                success = false;
                return MessagePrinter.printMessage(message, "The playlist does not exist").then().block();
            }
        }));
    }
}
