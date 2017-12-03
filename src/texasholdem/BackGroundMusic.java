package texasholdem;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jiach
 */
public class BackGroundMusic {

    private String mediaURL;
    private Media media;
    private MediaPlayer mediaPlayer;

    public BackGroundMusic() {
        mediaURL = new File("src/Media/人生は目玉焼きのよう.mp3").toURI().toString();
        media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    public BackGroundMusic(String fileName) {
        mediaURL = new File("src/Media/" + fileName + ".mp3").toURI().toString();
        media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    //gettter
    public String fileMediaURL() {
        return mediaURL;
    }

    public Media getMedia() {
        return media;
    }

    public MediaPlayer getMediaPlayer() {
        return mediaPlayer;
    }

    //setter
    public void SwitchMusic(String fileName) {
        mediaPlayer.stop();
        mediaURL = new File("src/Media/" + fileName + ".mp3").toURI().toString();
        media = new Media(mediaURL);
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        mediaPlayer.play();
    }

    /**
     * 10/21/17 Added so that when testing, and running the program over and
     * over again, the music will be quiet.
     */
    public void muteMusic() {
        System.out.println("MUSIC VOLUME SET TO ZERO FOR TESTING\n"
                + "REMEMBER TO COMMENT OUT THIS METHOD CALL!");
        mediaPlayer.setVolume(0);
    }
}
