package discord_bot.command_player.Chat.Commands.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.CommandsTypes.HelpCommand;

@Service
public class HelpCommandFactory implements CommandFactory<HelpCommand> {

    public HelpCommand createCommand() {
        return new HelpCommand();
    }
}
