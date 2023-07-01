package discord_bot.command_player.Chat.Commands;

public class PlayCommand extends Command {
    public PlayCommand() {
        super("!play",
                "Play music from provided link",
                new String[] { "https://www.youtube.com/watch?v=dQw4w9WgXcQ" });
    }
}
