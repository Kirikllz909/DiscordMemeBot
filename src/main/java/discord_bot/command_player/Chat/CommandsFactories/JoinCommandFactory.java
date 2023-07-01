package discord_bot.command_player.Chat.CommandsFactories;

import discord_bot.command_player.Chat.Commands.JoinCommand;

public class JoinCommandFactory implements CommandFactory<JoinCommand> {
    public JoinCommand createCommand() {
        return new JoinCommand();
    }
}
