package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.CommandsTypes.InfoCommand;
import reactor.core.publisher.Mono;

@Service
public class InfoCommandListener implements CommandListener<InfoCommand> {

    @Override
    public Class<InfoCommand> getCommandType() {
        return InfoCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message msg, String[] args) {
        return MessagePrinter.printMessage(msg, new InfoCommand().getDescription());
    }
}
