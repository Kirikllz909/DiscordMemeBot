package discord_bot.command_player.Listeners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.GetCommandArgs;
import discord_bot.command_player.Chat.Commands.Command;
import discord_bot.command_player.Chat.CommandsListeners.CommandListener;
import reactor.core.publisher.Mono;

public abstract class MessageListener<T extends Command> {

    @Autowired
    List<CommandListener<T>> listeners;

    public Mono<Void> processCommand(Message eventMessage) {
        if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
            if (eventMessage.getContent().length() > 0 && eventMessage.getContent().charAt(0) == '!')
                for (CommandListener<T> l : listeners) {
                    return l.processCommand(eventMessage, GetCommandArgs.getCommandArgs(eventMessage));
                }
        }
        return Mono.just(eventMessage).then();
    }
}
