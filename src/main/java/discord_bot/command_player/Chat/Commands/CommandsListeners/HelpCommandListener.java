package discord_bot.command_player.Chat.Commands.CommandsListeners;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.CommandsFactories.CommandFactory;
import discord_bot.command_player.Chat.Commands.CommandsTypes.Command;
import discord_bot.command_player.Chat.Commands.CommandsTypes.HelpCommand;
import reactor.core.publisher.Mono;

@Service
public class HelpCommandListener<T extends Command> implements CommandListener<HelpCommand> {

    @Autowired
    private List<CommandFactory<T>> commandFactories;

    public Class<HelpCommand> getCommandType() {
        return HelpCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message msg, String[] args) {
        String messageToPrint = "";
        for (CommandFactory<T> factory : commandFactories) {
            Command command = factory.createCommand();
            messageToPrint += "**" + command.getName() + "** \t\t\t" + command.getDescription() + "\n";
        }

        return MessagePrinter.printMessage(msg, messageToPrint);
    }

}
