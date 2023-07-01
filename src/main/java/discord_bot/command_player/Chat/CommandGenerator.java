package discord_bot.command_player.Chat;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import discord_bot.command_player.Chat.Commands.Command;
import discord_bot.command_player.Chat.CommandsFactories.CommandFactory;
import discord_bot.command_player.Chat.CommandsFactories.DefaultCommandFactory;

@Configuration
public class CommandGenerator<T extends Command> {

    @Autowired
    List<CommandFactory<T>> commandFactories;

    DefaultCommandFactory defaultFactory = new DefaultCommandFactory();

    public T generateCommand(String commandName) {
        for (CommandFactory<T> commandFactory : commandFactories) {
            T generatedCommand = commandFactory.createCommand();
            if (commandName.equalsIgnoreCase(generatedCommand.getName())) {
                return generatedCommand;
            }
        }
        return (T) defaultFactory.createCommand();
    }
}
