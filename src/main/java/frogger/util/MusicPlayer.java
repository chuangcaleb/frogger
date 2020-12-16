package frogger.util;

import frogger.constant.FilePath;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


import java.io.File;

/**
 * {@code MusicPlayer} is a singleton utility enum that handles background music.
 */
public enum MusicPlayer {
    /** The singleton instance of the MusicPlayer */
    INSTANCE;

    /** The background music object. */
    private MediaPlayer mediaPlayer;

    /** Plays background music. */
    public void playMusic() {

        Media sound = new Media(new File(FilePath.MUSIC_BG).toURI().toString());
        mediaPlayer = new MediaPlayer(sound);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

}
