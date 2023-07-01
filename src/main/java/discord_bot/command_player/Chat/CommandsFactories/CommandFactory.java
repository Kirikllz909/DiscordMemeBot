package discord_bot.command_player.Chat.CommandsFactories;

import discord_bot.command_player.Chat.Commands.Command;

public interface CommandFactory<T extends Command> {
    public T createCommand();
}
