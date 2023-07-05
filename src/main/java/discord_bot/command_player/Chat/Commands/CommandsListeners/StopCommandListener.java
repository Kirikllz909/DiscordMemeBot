package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord4j.core.object.entity.channel.VoiceChannel;
import discord4j.voice.VoiceConnection;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.StopCommand;
import reactor.core.publisher.Mono;

@Service
public class StopCommandListener implements CommandListener<StopCommand> {

    @Override
    public Class<StopCommand> getCommandType() {
        return StopCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {

        VoiceChannel voiceChannel = (VoiceChannel) BotConfiguration.getGuildAudioManager().getVoiceChannel();
        if (voiceChannel != null) {
            VoiceConnection connection = voiceChannel.getVoiceConnection().block();
            BotConfiguration.getGuildAudioManager().getScheduler().removeAllFromQueue();
            return connection.disconnect();
        } else
            return MessagePrinter.printMessage(message, "Bot isn't connected to any of voice channels");
    }
}
