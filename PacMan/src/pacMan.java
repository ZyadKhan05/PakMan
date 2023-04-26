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

        // Initialize the win class
        win win = new win();

        // Initialize the death class
        death death = new death();

        storybook storybook = new storybook();


        // Set the scene to the Menu page
        primaryStage.setScene(menu.menuScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        // ON ACTION EVENTS

        // Menu screen on action options
        menu.menuScene.setOnKeyPressed(e -> {
            // Space to start game
            if (KeyCode.SPACE == e.getCode()) {
                System.out.println("The game has started");
                primaryStage.setScene(gameScene);
                menu.mediaPlayer.stop();
                game.setGame();
            }
            // Escape to close game
            if (KeyCode.ESCAPE == e.getCode()) {
                primaryStage.close();
            }
            // Test for win screen
            if (KeyCode.W == e.getCode()) {
                win.setWin();
                primaryStage.setScene(win.winScene);
                primaryStage.setFullScreen(true);
                menu.mediaPlayer.stop();
            }
            // Test for death screen
            if (KeyCode.D == e.getCode()) {
                death.setDeath();
                primaryStage.setScene(death.deathScene);
                primaryStage.setFullScreen(true);
                menu.mediaPlayer.stop();
            }
            // Test for death screen
            if (KeyCode.S == e.getCode()) {
                storybook.setStorybook();
                primaryStage.setScene(storybook.storybookScene);
                primaryStage.setFullScreen(true);
                menu.mediaPlayer.stop();
                media = new Media(new File("Resources/music/storybook.wav").toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.seconds(5)));
            }
        });

        // Open pause screen in the game
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
            game.mediaPlayer.stop();
            menu.mediaPlayer.play();
            menu.mediaPlayer.setAutoPlay(true);
            menu.mediaPlayer.setOnEndOfMedia(() -> menu.mediaPlayer.seek(Duration.ZERO));
    });

        // Death screen button actions
        death.quitButton.setOnAction(event -> {
            primaryStage.close();
        });

        death.returnToMenuButton.setOnAction(event -> {
            primaryStage.setScene(menu.menuScene);
            // Temporarily the music is here: it stops the current music from the game and then plays the music for the menu
            death.mediaPlayer.stop();
            menu.mediaPlayer.play();
            menu.mediaPlayer.setAutoPlay(true);
            menu.mediaPlayer.setOnEndOfMedia(() -> menu.mediaPlayer.seek(Duration.ZERO));        });

        // Win screen button actions
        win.quitButton.setOnAction(event -> {
            primaryStage.close();
        });

        win.returnToMenuButton.setOnAction(event -> {
            primaryStage.setScene(menu.menuScene);
            win.mediaPlayer.stop();
            menu.mediaPlayer.play();
            menu.mediaPlayer.setAutoPlay(true);
            menu.mediaPlayer.setOnEndOfMedia(() -> menu.mediaPlayer.seek(Duration.ZERO));        });
    }
}
