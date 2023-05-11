// import the classes for the win class
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

// This class contains everything required to create the win page
class win {
    public static void main(String[] args) {
        Model won= new Model();
    }
    // Initialize text, vbox, hbox, scene, media, mediaplayer, and buttons used in the win screen
    Text title = new Text();
    VBox winVbox;
    HBox titlePage;
    Scene winScene;
    Media media;
    MediaPlayer mediaPlayer;
    Button quitButton = new Button("Quit");
    Button restartButton = new Button("Restart Game");

    // setWin method
    public void setWin() {
        // Set and style title text
        title.setText("You won, but at what cost");
        Font titleFont = Font.loadFont("file:PacMan/Resources/Font/OptimusPrinceps.ttf", 60);
        title.setFont(titleFont);
        title.setFill(Color.RED);

        // HBox for the title
        titlePage = new HBox(10, title);
        titlePage.setAlignment(Pos.TOP_CENTER);

        // Vbox for Win
        winVbox = new VBox(50, titlePage, restartButton, quitButton);
        winVbox.setAlignment(Pos.CENTER);
        winVbox.setStyle("-fx-background-color: black;");

        // Win scene
        winScene = new Scene(winVbox);

        // Media and mediaPlayer for the music
        media = new Media(new File("PacMan/Resources/music/win.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        // Quit button styles and hover properties
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

        // Return to menu button styles and hover properties
        restartButton.setFont(titleFont);
        restartButton.setTextFill(Color.WHITE);
        restartButton.setBackground(null);
        restartButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                restartButton.setStyle("-fx-text-fill: red;\n");
            } else {
                restartButton.setStyle("-fx-text-fill: grey");
            }
        });
    }
}
