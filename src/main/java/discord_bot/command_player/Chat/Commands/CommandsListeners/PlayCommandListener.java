package discord_bot.command_player.Chat.Commands.CommandsListeners;

import java.util.concurrent.Future;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.CommandsTypes.PlayCommand;
import discord_bot.command_player.Music.PlayerManager;
import discord_bot.command_player.Music.TrackLoadingHandler;
import reactor.core.publisher.Mono;

//TODO: after !play command return message track added to playlist

@Service
public class PlayCommandListener implements CommandListener<PlayCommand> {

    private boolean success = true;

    @Override
    public Class<PlayCommand> getCommandType() {
        return PlayCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {

        if (args == null || args.length == 0) {
            return MessagePrinter.printMessage(message, "There is no link to the song");
        }

        if (args.length > 1) {
            return MessagePrinter.printMessage(message,
                    "Too many arguments for command. Check !help for more information");
        }

        JoinCommandListener joinCommandListener = new JoinCommandListener();
        Mono<Void> afterJoining = joinCommandListener.processCommand(message, args);
        Future<Void> futureForLoading = PlayerManager.PLAYER_MANAGER.loadItem(
                args[0],
                new TrackLoadingHandler());

        return afterJoining.then(Mono.fromCallable(() -> {
            return futureForLoading.get();
        })).onErrorResume((throwable) -> MessagePrinter.printMessage(message, "The playlist does not exist"));
    }
}
