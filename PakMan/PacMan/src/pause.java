// Import all necessary classes for the pause screen
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

// This class contains everything required to create the pause page
class pause {

    // Declaration and initialization of labels, buttons, vbox, and scene used in the class
    Text pauseLabel = new Text("Game Paused");
    Button resumeButton = new Button("Resume");
    Button quitButton = new Button("Quit");
    VBox buttonsVbox;
    VBox pauseVbox;
    Scene pauseScene;

    // Method to set the pause scene
    public void setPause() {
        // Initialize the two font sizes
        Font largeFont = Font.loadFont("file:PacMan/Resources/Font/pac.ttf", 50);
        Font smallFont = Font.loadFont("file:PacMan/Resources/Font/pac.ttf", 25);

        // Set styles for the Pause Label
        pauseLabel.setFont(largeFont);
        pauseLabel.setFill(Color.YELLOW);

        // Set styles for both Resume and Quit Buttons
        resumeButton.setFont(smallFont);
        resumeButton.setStyle("-fx-text-fill: white;");
        resumeButton.setBackground(null);
        quitButton.setFont(smallFont);
        quitButton.setStyle("-fx-text-fill: white;");
        quitButton.setBackground(null);

        // Create and style vbox which contains only the two button options
        buttonsVbox = new VBox(15, resumeButton, quitButton);
        buttonsVbox.setAlignment(Pos.CENTER);
        buttonsVbox.setStyle("-fx-border-color: red; -fx-border-insets: 10; -fx-border-width: 3;-fx-border-style: dashed; ");

        // Create the pauseVbox with the label and the buttons vbox
        pauseVbox = new VBox(10, pauseLabel, buttonsVbox);
        pauseVbox.setAlignment(Pos.CENTER);
        pauseVbox.setStyle("-fx-background-color: black");

        // Create the new Scene
        pauseScene = new Scene(pauseVbox);

        // On hover properties for both buttons so that it changes text color when the mouse is over the button
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