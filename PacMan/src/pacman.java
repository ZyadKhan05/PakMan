
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.shape.Circle;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.input.KeyCode;
import javax.swing.*;

public class pacman extends Application {
    public static void main(String[] args){
        launch(args);
    }
    public void start(Stage primaryStage){
// Constants for the scene size (Change based on board)
        final double START_X = 100.0, START_Y = 40.0;
        final double RADIUS = 20.0;
        final double STEP = 10.0;
// Create pak
        Circle pak = new Circle(START_X, START_Y, RADIUS);
        Circle obstacle = new Circle(50.00,60.00,30);
        pak.setFill(Color.YELLOW);
        pak.setStroke(Color.YELLOW);
        Pane pane = new Pane(pak,obstacle);

        Scene scene = new Scene(pane, 600, 600);

        scene.setOnKeyPressed(event -> {
//Get the pak coordinates
            double x = pak.getCenterX();
            double y = pak.getCenterY();
//check for the down arrow
            if (event.getCode() == KeyCode.DOWN && y < 400 - RADIUS)
            {
                pak.setCenterY(y + STEP);
            }
            if (event.getCode() == KeyCode.S && y < 400 - RADIUS)
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
            if (event.getCode() == KeyCode.RIGHT && x < 400 - RADIUS)
            {
                pak.setCenterX(x + STEP);
            }
            if (event.getCode() == KeyCode.D && x < 400 - RADIUS)
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
// Set scene on the stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

