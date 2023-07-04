package discord_bot.command_player;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import discord4j.common.util.Snowflake;
import discord4j.core.DiscordClientBuilder;
import discord4j.core.GatewayDiscordClient;
import discord4j.core.event.domain.Event;

import discord_bot.command_player.Listeners.EventListener;
import discord_bot.command_player.Music.GuildAudioManager;

@Configuration
public class BotConfiguration {
    private static final Logger log = LoggerFactory.getLogger(BotConfiguration.class);
    private static GuildAudioManager guildAudioManager;

    public static void setGuildAudioManager(Snowflake id) {
        guildAudioManager = guildAudioManager.of(id);
    }

    public static GuildAudioManager getGuildAudioManager() {
        return guildAudioManager;
    }

    @Value("${token}")
    private String token;

    @Bean
    public <T extends Event> GatewayDiscordClient gatewayDiscordClient(List<EventListener<T>> eventListeners) {
        GatewayDiscordClient client = null;
        try {
            client = DiscordClientBuilder.create(token)
                    .build()
                    .login()
                    .block();

            for (EventListener<T> eventListener : eventListeners) {
                client.on(eventListener.getEventType())
                        .flatMap(eventListener::execute)
                        .onErrorResume(eventListener::handleError).subscribe();
            }
        } catch (Exception exception) {
            log.error("Be sure to use valid token", exception);
        }
        if (client != null)
            client.logout();
        return client;
    }
}
