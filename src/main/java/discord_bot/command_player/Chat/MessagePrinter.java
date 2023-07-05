package discord_bot.command_player.Chat;

import discord4j.core.object.entity.Message;
import reactor.core.publisher.Mono;

public class MessagePrinter {
    public static Mono<Void> printMessage(Message discordMsg, String message) {
        return Mono.just(discordMsg).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(message))
                .then();
    }
}
