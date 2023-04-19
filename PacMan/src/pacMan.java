import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.io.File;

// This program is the main program for the Pac-man Game

public class pacMan extends Application {
    public static void main(String args[]){
        // launch the application
        launch(args);
    }

    // Media player variables - Here temporarily
    Media media;
    MediaPlayer mediaPlayer;


    @Override
    public void start(Stage primaryStage) throws Exception{
        // Run the menu class
        menu menu = new menu();
        menu.setMenu();

        // Run the game class - Temporarily in the Main class
        game game = new game();
        Label playLabel = new Label("Score: " + game.score);
        playLabel.setStyle("-fx-font-size: 20px;-fx-text-fill: white; " );
        // PLACE HOLDER IMAGE
        Image pacMan = new Image("https://geometrydash.io/data/image/pacman-30th-anniversary.jpg");
        ImageView pacImage = new ImageView(pacMan);
        //setting the fit height and width of the Placeholder image
        pacImage.setFitHeight(455);
        pacImage.setFitWidth(500);
        // VBox for the game
        VBox gameVbox = new VBox(10, playLabel, pacImage);
        gameVbox.setAlignment(Pos.CENTER);
        gameVbox.setStyle("-fx-background-color: black;");
        // Game Scene
        Scene gameScene = new Scene(gameVbox);


        // Initialize the pause class
        pause pause = new pause();

        // Set the scene to the Menu page
        primaryStage.setScene(menu.menuScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        // When clicking space on the Main Menu, the application opens the game
        menu.menuScene.setOnKeyReleased(e -> {
            if (KeyCode.SPACE == e.getCode()) {
                System.out.println("The game has started");
                primaryStage.setScene(gameScene);
                menu.mediaPlayer.stop();
                // Temporarily the game music will be here - Will Want to Move to a game class
                // Stops the music from the menu and starts playing the game music
                media = new Media(new File("Resources/music/game.wav").toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
            }
        });
        // Escape to close the game on the Main Menu
        menu.menuScene.setOnKeyPressed(e -> {
            if (KeyCode.ESCAPE == e.getCode()) {
                primaryStage.close();
            }
        });

        // On event when in the game scene, pressing escape opens a pause menu
        gameScene.setOnKeyPressed(event -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                pause.setPause();
                primaryStage.setScene(pause.pauseScene);
                primaryStage.setFullScreen(true);
            }
        });

        // Pause menu events
        // Quit button closes application
        pause.quitButton.setOnAction(event -> {
            primaryStage.close();
        });
        // Resume button re-opens the gameScene
        pause.resumeButton.setOnAction(event -> {
            primaryStage.setScene(gameScene);
        });
        // Return to menu button sends player back to the menu
        pause.backToMenuButton.setOnAction(event -> {
            primaryStage.setScene(menu.menuScene);
            // Temporarily the music is here: it stops the current music from the game and then plays the music for the menu
            mediaPlayer.stop();
            mediaPlayer = new MediaPlayer(menu.media);
            mediaPlayer.play();
            mediaPlayer.setAutoPlay(true);
            mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
        });
    }
}
