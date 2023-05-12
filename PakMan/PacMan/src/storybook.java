// Import all necessary classes for the storybook page
import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

// This class contains everything required to create the storybook page
class storybook {
    // Declaration and initialization of text, vbox, image, iv, and scene
    Text storybookLabel = new Text("\n\n\n                   Once Upon a Time");
    Text storybookLabel2 = new Text("\n                      A group of coders were creating a game called PakMan. One of the coders allowed their\n" +
            "\n                      friend to test the game. This poor soul was a man called Mert.\n" +
            "\n                      As soon as Mert touched the keyboard, he vanished. While trying\n" +
            "\n                      to find where Mert went, the coders recognized a familiar face in the game.\n" +
            "\n                      Mert, soul now forever bound to code, destined to be the ultimate rival to Pak. \n" +
            "\n                      Hungry for vengeance, driven mad by his digital confinement,  Mert had become\n" +
            "\n                      reliant on the one thing that brings him joy, the pellets. Pakman will be tasked\n" +
            "\n                      to consume all of these pellets to put Mert out of his misery. As the coders of this game \n" +
            "\n                      we encourage you to play our game, but whatever you do, don't anger Mert.  \n" +
            "\n                                                       This is... Pakman"
    );
    VBox storybookVbox;
    Scene storybookScene;
    Image mert;
    ImageView mertIV;
    VBox mertVbox;


    // Method to set the pause scene
    public void setStorybook() {
        // Initialize the two font sizes
        Font largeFont = Font.loadFont("file:PacMan/Resources/Font/narrate.otf", 25);
        Font smallFont = Font.loadFont("file:PacMan/Resources/Font/narrate.otf", 20);

        // Set styles for the Pause Label
        storybookLabel.setFont(largeFont);
        storybookLabel.setFill(Color.BLACK);
        storybookLabel2.setFont(smallFont);
        storybookLabel2.setFill(Color.BLACK);

        // Fade Transition for the Once Upon a Time
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(12), storybookLabel);
        fadeTransition.setFromValue(8.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.play();

        // Lore transitions in after 13 seconds
        FadeTransition fadeIn = new FadeTransition(Duration.seconds(13), storybookLabel2);
        fadeIn.setFromValue(0.0);
        fadeIn.setToValue(12.0);
        fadeIn.play();

        // Image and ImageView for the Mert Image to be on the storybook
        mert = new Image("file:PacMan/Resources/Images/ghost.png");
        mertIV = new ImageView(mert);
        mertIV.setFitHeight(150);
        mertIV.setFitWidth(150);
        mertVbox = new VBox(0, mertIV);
        mertVbox.setAlignment(Pos.TOP_RIGHT);

        // VBox for the storybook page
        storybookVbox = new VBox(0, storybookLabel, storybookLabel2);
        storybookVbox.setStyle("-fx-background-image: url('https://media.istockphoto.com/id/1195781759/vector/realistic-vector-an-old-open-book-bound-in-leather-with-metal-corners-blank-pages-of-yellow.jpg?s=612x612&w=0&k=20&c=wpBMdwWBjKnRJMs9hP_iI0npda5nLaiViArSuxUyy98=') ; -fx-background-size: stretch");
        storybookVbox.setAlignment(Pos.TOP_LEFT);
        storybookVbox.getChildren().add(mertVbox);

        // Create the new Scene
        storybookScene = new Scene(storybookVbox);
    }
}