package discord_bot.command_player.Chat.CommandsFactories;

import discord_bot.command_player.Chat.Commands.DefaultCommand;

public class DefaultCommandFactory implements CommandFactory<DefaultCommand> {
    public DefaultCommand createCommand() {
        return new DefaultCommand();
    }
}
