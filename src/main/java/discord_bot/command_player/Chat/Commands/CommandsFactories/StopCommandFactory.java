package discord_bot.command_player.Chat.Commands.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.StopCommand;

@Service
public class StopCommandFactory implements CommandFactory<StopCommand> {
    @Override
    public StopCommand createCommand() {
        return new StopCommand();
    }
}
