package discord_bot.command_player.Chat.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.InfoCommand;

@Service
public class InfoCommandFactory implements CommandFactory<InfoCommand> {
    @Override
    public InfoCommand createCommand() {
        return new InfoCommand();
    }
}
