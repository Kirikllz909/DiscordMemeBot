package discord_bot.command_player.Chat;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.MessageChannel;
import reactor.core.publisher.Mono;

public class MessagePrinter {
    public static Mono<Void> printMessageInChannel(MessageChannel channel, String message) {
        return channel.createMessage(message)
                .then();
    }

    public static Mono<Void> printMessage(Message discordMsg, String message) {
        return Mono.just(discordMsg).flatMap(Message::getChannel)
                .flatMap(channel -> channel.createMessage(message))
                .then();
    }
}
