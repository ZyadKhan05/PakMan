import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.util.Duration;
import java.io.File;

// This program is the main program for the Pac-man Game

public class main extends Application {
    public static void main(String args[]){
        // launch the application
        launch(args);
    }

    // Media player variables - Here temporarily
    Media media;
    MediaPlayer mediaPlayer;


    @Override
    public void start(Stage primaryStage) throws Exception {
        // Run the menu class
        menu menu = new menu();
        menu.setMenu();

        // Run the game class - Temporarily in the Main class
        game game = new game();
        game.setBoard();
        Text playLabel = new Text("Score: " + game.score);
        Font titleFont = Font.loadFont("file:Resources/Font/pac.ttf", 30);
        //Setting the font
        playLabel.setFont(titleFont);
        playLabel.setFill(Color.BLACK);
        HBox textHBox = new HBox(10, playLabel);

        final double START_X = 100.0, START_Y = 40.0;
        final double RADIUS = 15.0;
        final double STEP = 10.0;

        // Create pak
        Circle pak = new Circle(START_X, START_Y, RADIUS);
        pak.setFill(Color.YELLOW);
        pak.setStroke(Color.YELLOW);
        Pane pane = new Pane(pak);
        pane.setStyle("-fx-background-color: black;");
        VBox gameVBox = new VBox(2, textHBox, pane);
        pane.setMinWidth(500);
        pane.setMinHeight(500);
        Scene gameScene = new Scene(gameVBox, 500, 535);


        // Initialize the pause class
        pause pause = new pause();

        // Initialize the win class
        win win = new win();

        // Initialize the death class
        death death = new death();

        // Initialize the storybook class
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
                game.setBoard();
                game.playMusic();
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
            menu.playMusic();
        });

        // Death screen button actions
        death.quitButton.setOnAction(event -> {
            primaryStage.close();
        });

        death.returnToMenuButton.setOnAction(event -> {
            primaryStage.setScene(menu.menuScene);
            // Temporarily the music is here: it stops the current music from the game and then plays the music for the menu
            death.mediaPlayer.stop();
            menu.playMusic();
        });

        // Win screen button actions
        win.quitButton.setOnAction(event -> {
            primaryStage.close();
        });

        win.returnToMenuButton.setOnAction(event -> {
            primaryStage.setScene(menu.menuScene);
            win.mediaPlayer.stop();
            menu.playMusic();
        });

        gameScene.setOnKeyPressed(event -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                pause.setPause();
                primaryStage.setScene(pause.pauseScene);
                primaryStage.setFullScreen(true);
            }
        //Get the pak coordinates
            double x = pak.getCenterX();
            double y = pak.getCenterY();
        //check for the down arrow
            if (event.getCode() == KeyCode.DOWN && y < 500 - RADIUS)
            {
                pak.setCenterY(y + STEP);
            }
            if (event.getCode() == KeyCode.S && y < 500 - RADIUS)
            {
                pak.setCenterY(y + STEP);
            }
        // Check for the up arrow
            if (event.getCode() == KeyCode.UP && y > RADIUS)
            {
                pak.setCenterY(y - STEP);
            }
            if (event.getCode() == KeyCode.W && y > RADIUS)
            {
                pak.setCenterY(y - STEP);
            }
        //Check for right arrow
            if (event.getCode() == KeyCode.RIGHT && x < 500 - RADIUS)
            {
                pak.setCenterX(x + STEP);
            }
            if (event.getCode() == KeyCode.D && x < 500 - RADIUS)
            {
                pak.setCenterX(x + STEP);
            }
        // check for left arrow
            if (event.getCode() == KeyCode.LEFT && x > RADIUS)
            {
                pak.setCenterX(x - STEP);
            }
            if (event.getCode() == KeyCode.A && x > RADIUS)
            {
                pak.setCenterX(x - STEP);
            }
        });
    }
}
