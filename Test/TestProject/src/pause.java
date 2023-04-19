import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

class pause {
    Scene pauseScene;

    Text pauseLabel = new Text("Game Paused");

    VBox pauseVbox;

    Button resumeButton = new Button("Resume");
    Button quitButton = new Button("Quit");

    public void setPause() {
        Font pacFont = Font.loadFont("file:TestProject/resources/Font/pac.ttf", 50);

        pauseLabel.setFont(pacFont);
        pauseLabel.setFill(Color.RED);

        resumeButton.setFont(pacFont);
        quitButton.setFont(pacFont);
        resumeButton.setStyle("-fx-text-fill: white;");
        quitButton.setStyle("-fx-text-fill: white;");


        resumeButton.setBackground(null);
        quitButton.setBackground(null);


        pauseVbox = new VBox(10, pauseLabel, resumeButton, quitButton);
        pauseVbox.setAlignment(Pos.CENTER);
        pauseVbox.setStyle("-fx-background-color: black;");

        pauseScene = new Scene(pauseVbox);

        resumeButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                resumeButton.setStyle("-fx-text-fill: blue;\n");
            } else {
                resumeButton.setStyle("-fx-text-fill: white ");
            }
        });

        quitButton.hoverProperty().addListener((obs, oldVal, newValue) -> {
            if (newValue) {
                quitButton.setStyle("-fx-text-fill: red;\n");
            } else {
                quitButton.setStyle("-fx-text-fill: white ");
            }
        });
    }
}
