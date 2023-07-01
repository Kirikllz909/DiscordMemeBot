package discord_bot.command_player.Chat.Commands;

public abstract class Command {
    protected String name;
    protected String description;
    protected String[] args;

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    Command(String name, String description, String[] args) {
        this.name = name;
        this.description = description;
        this.args = args;
    }
}
