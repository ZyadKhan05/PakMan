// Import javafx classes
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import java.io.File;

// This class contains everything required to create the menu page
class menu {

    // Declare and initialize text, hbox, vbox, labels, scene, media, and mediaplayer used in the menu page
    Text title = new Text();
    Text menuLabel = new Text();
    Text quitLabel = new Text();
    HBox titlePage;
    VBox buttonOptions;
    VBox credits;
    VBox vbox;
    Label creditsLabel = new Label("");
    Scene menuScene;
    Media media;
    MediaPlayer mediaPlayer;

    // Method to set the menu page
    public void setMenu() {
        // Set the title for the menu
        title.setText("PAKMAN");
        Font titleFont = Font.loadFont("file:PacMan/Resources/Font/pac.ttf", 60);
        //Setting the font
        title.setFont(titleFont);
        title.setFill(Color.YELLOW);

        // Font for the label options
        Font labelFont = Font.loadFont("file:PacMan/Resources/Font/pac.ttf", 18);
        menuLabel.setText("Push SPACE Key");
        quitLabel.setText("Push ESC to quit");
        menuLabel.setFont(labelFont);
        quitLabel.setFont(labelFont);
        menuLabel.setFill(Color.CYAN);
        quitLabel.setFill(Color.RED);

        // HBox for the title
        titlePage = new HBox(10, title);
        titlePage.setAlignment(Pos.TOP_CENTER);

        // VBox for the button options
        buttonOptions = new VBox(100, menuLabel, quitLabel);
        buttonOptions.setAlignment(Pos.CENTER);

        // Credit Label and VBox for the label
        creditsLabel.setText("Not \u00a9 2023 LTD does not exist.\n          All rights unreserved");
        creditsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px");
        credits = new VBox(50, creditsLabel);
        credits.setAlignment(Pos.BOTTOM_CENTER);

        // General VBox for all elements in the page
        vbox = new VBox(50, titlePage, buttonOptions);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(credits);
        vbox.setStyle("-fx-background-color: black;");

        // Set Scene
        menuScene = new Scene(vbox);

    }
    // Method used to play the music for the menu page
    public void playMusic() {
        media = new Media(new File("Pacman/Resources/music/menu.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
}