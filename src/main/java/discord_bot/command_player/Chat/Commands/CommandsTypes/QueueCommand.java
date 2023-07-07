package discord_bot.command_player.Chat.Commands.CommandsTypes;

public class QueueCommand extends Command {
    public QueueCommand() {
        super("!queue",
                "Returns a list of tracks that currently are in the queue",
                null);
    }

}
