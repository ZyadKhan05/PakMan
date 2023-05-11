// Import all classes needed for the main class
import javafx.animation.PauseTransition;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.File;

// This project was created by Ken Wei, Virginia Johnston, Colton Swanson, and Zyad Khan
// This project is the Group Project for CIS-232-01 which was completed on 05/09/2023
// The purpose for this project is to use a combination of JavaFX and Javax swing to create Pakman with different screens
// that would typically be in a video game
public class main extends Application {
    public static void main(String args[]) {
        // launch the application
        launch(args);
    }

    // Media and MediaPlayer variables
    Media media;
    MediaPlayer mediaPlayer;

    // PauseTransition for the Timer
    PauseTransition timerPause;
    int musicCount;

    // Initialize the different classes that will be used
    menu menu = new menu();

    death death = new death();
    storybook storybook = new storybook();
    game game = new game();


    // Start Method
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Run the setMenu and setStorybook method
        menu.setMenu();
        storybook.setStorybook();

        musicCount = 0;
        // Play music on first loop
        if (musicCount <= 0) {
            musicCount = 1;
            menu.playMusic();
        }

        // Set the timer to transition in 5 seconds
        timerPause = new PauseTransition(Duration.seconds(30));

        // Set the scene to the Menu page
        primaryStage.setScene(menu.menuScene);
        primaryStage.setFullScreen(true);
        primaryStage.show();


        // Menu screen on action options
        menu.menuScene.setOnKeyPressed(e -> {
            // Space to start game
            if (KeyCode.SPACE == e.getCode()) {
                // Space will start the storybook and play the music for the storybook, will also start the timer
                primaryStage.setScene(storybook.storybookScene);
                primaryStage.setFullScreen(true);
                menu.mediaPlayer.stop();
                media = new Media(new File("PacMan/Resources/music/storybook.wav").toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setAutoPlay(true);
                mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.seconds(5)));
                timerPause.play();
            }
            // Escape to close game from the main menu
            if (KeyCode.ESCAPE == e.getCode()) {
                primaryStage.close();
            }
        });

        // After the timer is complected or space key is pressed on the storybook page, run the Pakman file which starts the game.
        storybook.storybookScene.setOnKeyPressed(e -> {
            if (KeyCode.SPACE == e.getCode()) {
                pakman.main(new String[0]);
                mediaPlayer.stop();
                primaryStage.setIconified(true);
            } else{
                timerPause.setOnFinished(event -> {
                    pakman.main(new String[0]);
                    mediaPlayer.stop();
                    primaryStage.setIconified(true);
        });
        }});

        // Test for death screen
        if (Model.dead) {
            death.setDeath();
            primaryStage.setScene(death.deathScene);
            primaryStage.setFullScreen(true);
            menu.mediaPlayer.stop();
        }
    }
    // Method to stop the music for menu and game classes
    public void stopMusic(){
        if (game != null && game.mediaPlayer != null) {
            game.mediaPlayer.stop();
        }
        if (menu != null && menu.mediaPlayer != null) {
            menu.mediaPlayer.stop();
        }
    }
}