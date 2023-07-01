package discord_bot.command_player.Chat.CommandsListeners;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.Commands.JoinCommand;
import reactor.core.publisher.Mono;

public class JoinCommandListener implements CommandListener<JoinCommand> {

    public Class<JoinCommand> getCommandType() {
        return JoinCommand.class;
    }

    public Mono<Void> processCommand(Message msg, String[] args) {
        return Mono.just(0).then();
    }
}
