package discord_bot.command_player.Classes;

public abstract class Command {
    private String name;
    private String msgToSend;

    Command(String name, String msgToSend) {
        this.name = name;
        this.msgToSend = msgToSend;
    }
}
