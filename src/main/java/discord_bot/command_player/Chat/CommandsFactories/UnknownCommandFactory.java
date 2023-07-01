package discord_bot.command_player.Chat.CommandsFactories;

import discord_bot.command_player.Chat.Commands.UnknownCommand;

public class UnknownCommandFactory implements CommandFactory<UnknownCommand> {
    public UnknownCommand createCommand() {
        return new UnknownCommand();
    }
}
