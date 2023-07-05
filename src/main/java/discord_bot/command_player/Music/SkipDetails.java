package discord_bot.command_player.Music;

import com.sedmelluq.discord.lavaplayer.track.AudioTrack;

public class SkipDetails {
    private boolean isSkipped = false;
    private AudioTrack skippedTrack = null;

    public SkipDetails(boolean isSkipped, AudioTrack skippedTrack) {
        this.isSkipped = isSkipped;
        this.skippedTrack = skippedTrack;
    }

    public boolean getIsSkipped() {
        return isSkipped;
    }

    public AudioTrack getSkippedTrack() {
        return skippedTrack;
    }
}
