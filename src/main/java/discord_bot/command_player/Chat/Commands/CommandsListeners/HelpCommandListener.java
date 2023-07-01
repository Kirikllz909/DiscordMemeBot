package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.Commands.HelpCommand;
import reactor.core.publisher.Mono;

@Service
public class HelpCommandListener implements CommandListener<HelpCommand> {
    public Class<HelpCommand> getCommandType() {
        return HelpCommand.class;
    }

    public Mono<Void> processCommand(Message msg, String[] args) {
        return Mono.just(0).then();
    }

}