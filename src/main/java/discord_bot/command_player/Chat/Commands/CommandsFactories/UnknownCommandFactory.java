package discord_bot.command_player.Chat.Commands.CommandsFactories;

import discord_bot.command_player.Chat.Commands.CommandsTypes.UnknownCommand;

public class UnknownCommandFactory implements CommandFactory<UnknownCommand> {
    public UnknownCommand createCommand() {
        return new UnknownCommand();
    }
}
