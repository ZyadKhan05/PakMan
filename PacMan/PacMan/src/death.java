// Import all classes
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.control.*;
import java.io.File;

// This class
class death {
    // Initialize all variables used in the death screen
    Text title = new Text();
    VBox deathVbox;
    HBox titlePage;
    Scene deathScene;
    Media media;
    MediaPlayer mediaPlayer;
    Button quitButton = new Button("Quit");
    Button returnToMenuButton = new Button("Return to Menu");

    // setDeath method
    public void setDeath() {
        // Set and style the title text
        title.setText("You died");
        Font titleFont = Font.loadFont("file:Resources/Font/OptimusPrinceps.ttf", 60);
        //Setting the font
        title.setFont(titleFont);
        title.setFill(Color.RED);

        // HBox for the title
        titlePage = new HBox(10, title);
        titlePage.setAlignment(Pos.TOP_CENTER);

        // VBox for the death screen
        deathVbox = new VBox(50, titlePage, returnToMenuButton, quitButton);
        deathVbox.setAlignment(Pos.CENTER);
        deathVbox.setStyle("-fx-background-color: black;");

        // Death Scene
        deathScene = new Scene(deathVbox);

        // Media and mediaPlayer for the music
        media = new Media(new File("Resources/music/death.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        // Quit button
        quitButton.setFont(titleFont);
        quitButton.setTextFill(Color.GREY);
        quitButton.setBackground(null);
        quitButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                quitButton.setStyle("-fx-text-fill: #8B0000");
            } else {
                quitButton.setStyle("-fx-text-fill: grey");
            }
        });

        // Return to Menu button
        returnToMenuButton.setFont(titleFont);
        returnToMenuButton.setTextFill(Color.GREY);
        returnToMenuButton.setBackground(null);
        returnToMenuButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                returnToMenuButton.setStyle("-fx-text-fill: #8B0000;");
            } else {
                returnToMenuButton.setStyle("-fx-text-fill: grey");
            }
        });
    }
}
