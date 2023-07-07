package discord_bot.command_player.Listeners;

import org.springframework.stereotype.Service;

import discord4j.core.event.domain.VoiceStateUpdateEvent;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.voice.VoiceConnection;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Music.AudioTrackScheduler;
import reactor.core.publisher.Mono;

@Service
public class VoiceStateUpdateEventListener implements EventListener<VoiceStateUpdateEvent> {

    @Override
    public Class<VoiceStateUpdateEvent> getEventType() {
        return VoiceStateUpdateEvent.class;
    }

    @Override
    public Mono<Void> execute(VoiceStateUpdateEvent event) {
        if (BotConfiguration.getGuildAudioManager() == null)
            return Mono.just(0).then();
        VoiceChannel voiceChannel = (VoiceChannel) BotConfiguration.getGuildAudioManager().getVoiceChannel();

        Boolean isBotAlone = voiceChannel.getVoiceStates()
                .count()
                .map(count -> 1L == count).block();

        if (isBotAlone) {
            if (voiceChannel.getVoiceStates().next().block().getMember().block().isBot()) {
                VoiceConnection connection = voiceChannel.getVoiceConnection().block();
                AudioTrackScheduler scheduler = BotConfiguration.getGuildAudioManager().getScheduler();
                scheduler.removeAllFromQueue();
                scheduler.setCurrentTrack(null);
                return connection.disconnect();
            }
        }
        return Mono.just(0).then();
    }
}
