package discord_bot.command_player.Chat.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.Chat.Commands.DefaultCommand;
import reactor.core.publisher.Mono;

@Service
public class DefaultCommandListener implements CommandListener<DefaultCommand> {
    @Override
    public Class<DefaultCommand> getCommandType() {
        return DefaultCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message msg, String[] args) {
        return Mono.just(msg).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(new DefaultCommand().getDescription()))
                .then();
    }
}
