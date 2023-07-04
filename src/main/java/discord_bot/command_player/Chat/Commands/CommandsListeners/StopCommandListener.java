package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.Commands.StopCommand;
import reactor.core.publisher.Mono;

//TODO: leave the channel and remove all from queue

@Service
public class StopCommandListener implements CommandListener<StopCommand> {

    @Override
    public Class<StopCommand> getCommandType() {
        return StopCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {
        return Mono.just(0).then();
    }
}
