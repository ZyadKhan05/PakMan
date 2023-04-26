// import the classes
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
class win {
    // Initialize all variables used in the win screen
    Text title = new Text();
    VBox winVbox;
    HBox titlePage;
    Scene winScene;
    Media media;
    MediaPlayer mediaPlayer;
    Button quitButton = new Button("Quit");
    Button returnToMenuButton = new Button("Return to Menu");

    // setWin method
    public void setWin() {
        // Set and style title text
        title.setText("You won, but at what cost");
        Font titleFont = Font.loadFont("file:Resources/Font/OptimusPrinceps.ttf", 60);
        title.setFont(titleFont);
        title.setFill(Color.RED);

        // HBox for the title
        titlePage = new HBox(10, title);
        titlePage.setAlignment(Pos.TOP_CENTER);

        // Vbox for Win
        winVbox = new VBox(50, titlePage, returnToMenuButton, quitButton);
        winVbox.setAlignment(Pos.CENTER);
        winVbox.setStyle("-fx-background-color: black;");

        // Win scene
        winScene = new Scene(winVbox);

        // Media and mediaPlayer for the music
        media = new Media(new File("Resources/music/win.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        // Quit button
        quitButton.setFont(titleFont);
        quitButton.setTextFill(Color.WHEAT);
        quitButton.setBackground(null);
        quitButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                quitButton.setStyle("-fx-text-fill: red;\n");
            } else {
                quitButton.setStyle("-fx-text-fill: white ");
            }
        });

        // Return to menu button
        returnToMenuButton.setFont(titleFont);
        returnToMenuButton.setTextFill(Color.WHITE);
        returnToMenuButton.setBackground(null);
        returnToMenuButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                returnToMenuButton.setStyle("-fx-text-fill: red;\n");
            } else {
                returnToMenuButton.setStyle("-fx-text-fill: grey");
            }
        });

    }
}
