package frogger.util;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;

/**
 * {@code MusicPlayer} is a singleton utility enum "class" that handles background music.
 */
public enum MusicPlayer {
    INSTANCE;

    /** {@link MediaPlayer} is the object that handles background music. */
    private MediaPlayer mediaPlayer;

    /**
     * Plays background music.
     */
    public void playMusic() {
        // migrate constant string to its own dictionary class
        String musicFile = "src/main/resources/frogger/music/Frogger Main Song Theme (loop).mp3";

        Media sound = new Media(new File(musicFile).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * Stops background music
     */
    public void stopMusic() {
        mediaPlayer.stop();
    }
}
