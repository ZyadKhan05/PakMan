import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

// This class will be used to set the Game screen
class game {
    // Initialize all variables used in the menu
    int score = 0;
    Media media;
    MediaPlayer mediaPlayer;

    // setGame method
    public void setGame(){
        // Set the media and media player for the music
        media = new Media(new File("Resources/music/game.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
}
