import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.SequentialTransition;
import  javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.io.File;

// This class is used to set the Pause Page when in the game
class storybook {

    // Initialization of variables
    Text storybookLabel = new Text("\n          Once Upon\n          a Time");
    VBox storybookVbox;
    Scene storybookScene;
    Media media;
    MediaPlayer mediaPlayer;

    // Method to set the pause scene
    public void setStorybook() {
        // Initialize the two font sizes
        Font largeFont = Font.loadFont("file:Resources/Font/storybook.ttf", 50);
        Font smallFont = Font.loadFont("file:Resources/Font/storybook.ttf", 25);

        // Set styles for the Pause Label
        storybookLabel.setFont(largeFont);
        storybookLabel.setFill(Color.BLACK);
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(20), storybookLabel);
        fadeTransition.setFromValue(10.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();


        storybookVbox = new VBox(50, storybookLabel);
        storybookVbox.setStyle("-fx-background-image: url('https://media.istockphoto.com/id/1195781759/vector/realistic-vector-an-old-open-book-bound-in-leather-with-metal-corners-blank-pages-of-yellow.jpg?s=612x612&w=0&k=20&c=wpBMdwWBjKnRJMs9hP_iI0npda5nLaiViArSuxUyy98=') ; -fx-background-size: stretch");
        storybookVbox.setAlignment(Pos.BASELINE_LEFT);



        // Create the new Scene
        storybookScene = new Scene(storybookVbox);

        // Media and mediaPlayer for the music
        media = new Media(new File("Resources/music/storybook.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
}