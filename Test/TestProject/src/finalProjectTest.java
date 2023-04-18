import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.*;


public class finalProjectTest extends Application {
    public static void main(String args[]){
        // launch the application
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) {
        // Run the gameMenu class
        menu gameMenu = new menu();
        gameMenu.setMenu();

        // Run the game class
        game game = new game();
        Label playLabel = new Label("Welcome to the Game");
        HBox playHbox = new HBox(10, playLabel);
        playHbox.setAlignment(Pos.CENTER);
        Scene playScene = new Scene(playHbox);

        // Run the pause class
        pause pause = new pause();

        primaryStage.setScene(gameMenu.scene);
        primaryStage.setFullScreen(true);
        primaryStage.show();

        gameMenu.scene.setOnKeyReleased(e -> {
            if (KeyCode.SPACE == e.getCode()) {
                System.out.println("The game has started");
                game.setGame();
                primaryStage.setScene(playScene);
                gameMenu.mediaPlayer.stop();
            }
        });

        gameMenu.scene.setOnKeyPressed(e -> {
            if (KeyCode.ESCAPE == e.getCode()) {
                System.out.println("The game has ended");
                primaryStage.close();
            }
        });

        playScene.setOnKeyPressed(event -> {
            if (KeyCode.ESCAPE == event.getCode()) {
                pause.setPause();
                primaryStage.setScene(pause.pauseScene);
                primaryStage.setFullScreen(true);
                primaryStage.show();
            }
        });

        pause.quitButton.setOnAction(event -> {
            primaryStage.close();
        });
    }
}

