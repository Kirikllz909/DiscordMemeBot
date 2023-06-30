package discord_bot.command_player.Chat.Commands;

public abstract class Command {
    protected String name;
    protected String description;
    protected String[] args;

    Command(String name, String description, String[] args) {
        this.name = name;
        this.description = description;
        this.args = args;
    }
}
