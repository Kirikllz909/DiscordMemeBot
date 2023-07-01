package discord_bot.command_player.Chat.CommandsFactories;

import discord_bot.command_player.Chat.Commands.HelpCommand;

public class HelpCommandFactory implements CommandFactory<HelpCommand> {

    public HelpCommand createCommand() {
        return new HelpCommand();
    }
}
