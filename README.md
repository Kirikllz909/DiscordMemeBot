# DiscordMemeBot
This is Discord bot for playing in music channels

# How to launch the bot
You need to create the file `application.properties` in the folder `src\main\resources`. In the properties file you should add line:
`token=YOUR_TOKEN_GENERATED_ON_DISCORD_DEV_PORTAL`

# Available commands type
- `!help` - provides information about all bot commands
- `!info` - general information about bot
- `!join` - joins the voice channel of the user who wrote the message
- `!play` - plays music by provided link
- `!queue` - returns a list of tracks that are currently in the queue
- `!skip` - skips current track
- `!stop` - stops the player and disconnects from voice channel

# How to add commands
If you want to add new commands, you should do these steps:
1. Add new class which extends `Command` to the folder `CommandsTypes`
2. Add new factory which implements `CommandFactory<YourClass>` to the folder `CommandsFactories`
3. Add new listener which implements `CommandListener<YourClass>` to the folder `CommandsListeners` and also:
   1. Override function `Mono<Void> processCommand(Message message, String[] args)`
   2. Override function `Class<T> getCommandType()` 
