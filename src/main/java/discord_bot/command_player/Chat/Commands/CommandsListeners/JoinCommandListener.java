package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.VoiceState;
import discord4j.core.object.entity.Member;
import discord4j.core.object.entity.Message;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.Commands.JoinCommand;
import reactor.core.publisher.Mono;

@Service
public class JoinCommandListener implements CommandListener<JoinCommand> {

    @Override
    public Class<JoinCommand> getCommandType() {
        return JoinCommand.class;
    }

    private Member getMember(Message msg) {
        return msg.getAuthor().get().asMember(msg.getGuildId().get()).block();
    }

    @Override
    public Mono<Void> processCommand(Message msg, String[] args) {
        BotConfiguration.getGuildAudioManager()
                .setVoiceChannel(getMember(msg).getVoiceState().block().getChannel().block());
        return Mono.justOrEmpty(getMember(msg))
                .flatMap(Member::getVoiceState)
                .flatMap(VoiceState::getChannel)
                .flatMap(channel -> channel
                        .join(spec -> spec.setProvider(BotConfiguration.getGuildAudioManager().getProvider())))
                .then();
    }
}
