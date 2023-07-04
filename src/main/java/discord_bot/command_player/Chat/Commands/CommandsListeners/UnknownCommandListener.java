package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.Commands.UnknownCommand;
import reactor.core.publisher.Mono;

@Service
public class UnknownCommandListener implements CommandListener<UnknownCommand> {
    @Override
    public Class<UnknownCommand> getCommandType() {
        return UnknownCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message msg, String[] args) {
        return Mono.just(msg).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(new UnknownCommand().getDescription()))
                .then();
    }
}
