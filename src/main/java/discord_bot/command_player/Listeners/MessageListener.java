package discord_bot.command_player.Listeners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.CommandGenerator;
import discord_bot.command_player.Chat.CommandParser;
import discord_bot.command_player.Chat.Commands.CommandsListeners.CommandListener;
import discord_bot.command_player.Chat.Commands.CommandsTypes.Command;
import reactor.core.publisher.Mono;

public abstract class MessageListener<T extends Command> {

    @Autowired
    List<CommandListener<T>> listeners;

    @Autowired
    CommandGenerator<T> commandGenerator;

    public Mono<Void> processCommand(Message eventMessage) {
        BotConfiguration.setGuildAudioManager(eventMessage.getGuildId().get());
        if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {
            if (eventMessage.getContent().length() > 0 && eventMessage.getContent().charAt(0) == '!') {
                for (CommandListener<T> listener : listeners) {
                    if (commandGenerator.generateCommand(CommandParser.getCommandName(eventMessage))
                            .getClass() == listener.getCommandType())
                        return listener.processCommand(eventMessage, CommandParser.getCommandArgs(eventMessage));
                }
            }
        }
        return Mono.just(eventMessage).then();
    }
}
