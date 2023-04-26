import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.util.Duration;

import java.io.File;

// This class will be used to set the Game screen
class game {
    // Initialize all variables used in the menu
    int score = 0;
    Media media;
    AudioClip mediaPlayer;
    VBox board;
    Pane pane;
    Scene gameScene;

    // setGame method
    public void setGame(){
        final double START_X = 100.0, START_Y = 40.0;
        final double RADIUS = 20.0;
        final double STEP = 10.0;

        // Create pak
        Circle pak = new Circle(START_X, START_Y, RADIUS);
        Circle obstacle = new Circle(50.00,60.00,30);
        pak.setFill(Color.YELLOW);
        pak.setStroke(Color.YELLOW);
        pane = new Pane(pak,obstacle);
        gameScene = new Scene(pane, 500, 500);


    }

    Circle pak;
    Circle obstacle;
    final double START_X = 200.0, START_Y = 150.0;
    final double RADIUS = 20.0;
    final double STEP = 10.0;
    public void setBoard(){
        // Constants for the scene size (Change based on board)

// Create pak
        pak = new Circle(START_X, START_Y, RADIUS);
        obstacle = new Circle(50.00,60.00,30);
        pak.setFill(Color.YELLOW);
        pak.setStroke(Color.YELLOW);
        Pane pane = new Pane(pak,obstacle);
        pane.setStyle("-fx-background-color: black;");

    }
    public void playMusic(){
        media = new Media(new File("Resources/music/game.wav").toURI().toString());
        mediaPlayer = new AudioClip(media.getSource());
        mediaPlayer.play();
        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    }
}
