// Import all necessary classes
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.control.*;
import java.io.File;

// This class contains everything required to create the death page
class death {
    // Initialize text, vbox, hbox, scene, media, mediaPlayer, and buttons used in the death screen
    Text title = new Text();
    VBox deathVbox;
    HBox titlePage;
    Scene deathScene;
    Media media;
    MediaPlayer mediaPlayer;
    Button quitButton = new Button("Quit");
    Button restartButton = new Button("Restart Game");

    // setDeath method
    public void setDeath() {
        // Set and style the title text
        title.setText("You died");
        // Load font from project resources
        Font titleFont = Font.loadFont("file:PacMan/Resources/Font/OptimusPrinceps.ttf", 60);
        //Setting the font
        title.setFont(titleFont);
        title.setFill(Color.RED);

        // HBox for the title
        titlePage = new HBox(10, title);
        titlePage.setAlignment(Pos.TOP_CENTER);

        // VBox for the death screen
        deathVbox = new VBox(50, titlePage, restartButton, quitButton);
        deathVbox.setAlignment(Pos.CENTER);
        deathVbox.setStyle("-fx-background-color: black;");

        // Death Scene
        deathScene = new Scene(deathVbox);

        // Media and mediaPlayer for the music
        media = new Media(new File("PacMan/Resources/music/death.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        // Quit button
        quitButton.setFont(titleFont);
        quitButton.setTextFill(Color.GREY);
        quitButton.setBackground(null);
        // On hover property for the quit button
        quitButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                quitButton.setStyle("-fx-text-fill: #8B0000");
            } else {
                quitButton.setStyle("-fx-text-fill: grey");
            }
        });

        // Return to Menu button
        restartButton.setFont(titleFont);
        restartButton.setTextFill(Color.GREY);
        restartButton.setBackground(null);
        // On hover property for the restart button
        restartButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                restartButton.setStyle("-fx-text-fill: #8B0000;");
            } else {
                restartButton.setStyle("-fx-text-fill: grey");
            }
        });
    }
}
