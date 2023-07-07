package discord_bot.command_player.Chat.Commands.CommandsListeners;

import org.springframework.stereotype.Service;

import discord4j.core.object.entity.Message;
import discord_bot.command_player.BotConfiguration;
import discord_bot.command_player.Chat.MessagePrinter;
import discord_bot.command_player.Chat.Commands.CommandsTypes.SkipCommand;
import discord_bot.command_player.Music.SkipDetails;
import reactor.core.publisher.Mono;

@Service
public class SkipCommandListener implements CommandListener<SkipCommand> {

    @Override
    public Class<SkipCommand> getCommandType() {
        return SkipCommand.class;
    }

    @Override
    public Mono<Void> processCommand(Message message, String[] args) {
        SkipDetails details = BotConfiguration.getGuildAudioManager().getScheduler().skip();
        if (details.getIsSkipped())
            return MessagePrinter.printMessage(message,
                    "Track: **" + details.getSkippedTrack().getInfo().title + "** is skipped");
        else
            return MessagePrinter.printMessage(message, "Unable to skip track");
    }

}
