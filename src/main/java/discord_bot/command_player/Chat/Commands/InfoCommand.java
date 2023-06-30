package discord_bot.command_player.Chat.Commands;

public class InfoCommand extends Command {
    public String getDescription() {
        return this.description;
    }

    public InfoCommand() {
        super("!info",
                "This is bot for playing music on channels also it can play meme sounds which you are added.",
                null);
    }

}
