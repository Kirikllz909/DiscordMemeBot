package discord_bot.command_player.Listeners;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public abstract class MessageListener {
    String msgToSend = "Default message";

    public Mono<Void> processCommand(Message eventMessage) {
        if (eventMessage.getAuthor().map(user -> !user.isBot()).orElse(false)) {

            switch (eventMessage.getContent().toLowerCase()) {
                case "!info":
                    msgToSend = "Info message";
                    break;
            }
            return Mono.just(eventMessage).flatMap(Message::getChannel)
                    .flatMap(channel -> channel.createMessage(msgToSend)).then();
        }
        return Mono.just(eventMessage).then();
    }
}
