package discord_bot.command_player.Chat.Commands.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.CommandsTypes.JoinCommand;

@Service
public class JoinCommandFactory implements CommandFactory<JoinCommand> {
    public JoinCommand createCommand() {
        return new JoinCommand();
    }
}
