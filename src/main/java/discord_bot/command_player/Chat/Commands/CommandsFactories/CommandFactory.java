package discord_bot.command_player.Chat.Commands.CommandsFactories;

import discord_bot.command_player.Chat.Commands.CommandsTypes.Command;

public interface CommandFactory<T extends Command> {
    public T createCommand();
}
