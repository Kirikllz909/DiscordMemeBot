package discord_bot.command_player.Chat.Commands.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.CommandsTypes.SkipCommand;

@Service
public class SkipCommandFactory implements CommandFactory<SkipCommand> {
    @Override
    public SkipCommand createCommand() {
        return new SkipCommand();
    }

}
