package discord_bot.command_player.Chat.CommandsListeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.Commands.Command;
import reactor.core.publisher.Mono;

public interface CommandListener<T extends Command> {
    Logger LOG = LoggerFactory.getLogger(CommandListener.class);

    Class<T> getCommandType();

    Mono<Void> processCommand(Message message, String[] args);

    default Mono<Void> handleError(Throwable error) {
        LOG.error("Unable to process" + getCommandType().getSimpleName(), error);
        return Mono.empty();
    }
}
