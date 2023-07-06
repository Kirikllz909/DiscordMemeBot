package discord_bot.command_player.Chat.Commands.CommandsFactories;

import org.springframework.stereotype.Service;

import discord_bot.command_player.Chat.Commands.QueueCommand;

@Service
public class QueueCommandFactory implements CommandFactory<QueueCommand> {
    @Override
    public QueueCommand createCommand() {
        return new QueueCommand();
    }
}
