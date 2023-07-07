package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.CommandsTypes.UnknownCommand;
import reactor.core.publisher.Mono;

@Service
public class UnknownCommandListener implements CommandListener<UnknownCommand> {
    @Override
    public Class<UnknownCommand> getCommandType() {
        return UnknownCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message msg, String[] args) {
        return MessagePrinter.printMessage(msg, new UnknownCommand().getDescription());
    }
}
