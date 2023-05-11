// Import all necessary classes
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import java.io.File;

// This class contains the method to play the game music using Media and MediaPlayer from JavaFX
class game {
    // Initialize the media and mediaplayer used to set the game music
    Media media = new Media(new File("PacMan/Resources/music/mertWaka.wav").toURI().toString());;
    MediaPlayer mediaPlayer  = new MediaPlayer(media);

    // Method to play the game music
    public void playMusic(){
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
    // Mertageddon Music
    public void playMertageddonMusic(){
        Media media = new Media(new File("PacMan/Resources/music/Mertageddon.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
    // Method to stop the game music
    public void stopMusic() {
        mediaPlayer.stop();
    }
}
