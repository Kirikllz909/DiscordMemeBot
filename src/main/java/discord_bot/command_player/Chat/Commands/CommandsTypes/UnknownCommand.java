package discord_bot.command_player.Chat.Commands.CommandsTypes;

public class UnknownCommand extends Command {
    public UnknownCommand() {
        super(null,
                "Unknown command. Check !help for command list",
                null);
    }
}
