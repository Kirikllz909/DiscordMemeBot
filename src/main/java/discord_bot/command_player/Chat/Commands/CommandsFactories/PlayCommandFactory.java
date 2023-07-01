package discord_bot.command_player.Chat.Commands.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.PlayCommand;

@Service
public class PlayCommandFactory implements CommandFactory<PlayCommand> {
    @Override
    public PlayCommand createCommand() {
        return new PlayCommand();
    }
}
