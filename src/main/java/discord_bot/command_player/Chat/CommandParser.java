package discord_bot.command_player.Chat;

import java.util.Arrays;
import discord4j.core.object.entity.Message;

public class CommandParser {
    public static String getCommandName(Message msg) {
        String[] args = msg.getContent().split(" ");
        return args[0];
    }

    public static String[] getCommandArgs(Message msg) {
        String[] args = msg.getContent().split(" ");
        return args.length > 1 ? Arrays.copyOfRange(args, 1, args.length) : null;
    }
}
