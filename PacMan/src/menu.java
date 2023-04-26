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

// This class
class menu {

    // Initialize all variables used in the menu
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

    public void setMenu() {
        // Set the title for the menu
        title.setText("Not PACMAN");
        Font titleFont = Font.loadFont("file:Resources/Font/pac.ttf", 60);
        //Setting the font
        title.setFont(titleFont);
        title.setFill(Color.YELLOW);

        Font labelFont = Font.loadFont("file:Resources/Font/pac.ttf", 18);
        menuLabel.setText("Push SPACE Key");
        quitLabel.setText("Push ESC to quit");
        menuLabel.setFont(labelFont);
        quitLabel.setFont(labelFont);
        menuLabel.setFill(Color.CYAN);
        quitLabel.setFill(Color.RED);

        titlePage = new HBox(10, title);
        titlePage.setAlignment(Pos.TOP_CENTER);

        buttonOptions = new VBox(100, menuLabel, quitLabel);
        buttonOptions.setAlignment(Pos.CENTER);

        creditsLabel.setText("Not \u00a9 2023 LTD does not exist.\n          All rights unreserved");
        creditsLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px");

        credits = new VBox(50, creditsLabel);
        credits.setAlignment(Pos.BOTTOM_CENTER);

        vbox = new VBox(50, titlePage, buttonOptions);
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(credits);

        // Music
        media = new Media(new File("Resources/music/menu.wav").toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));

        menuScene = new Scene(vbox);
        vbox.setStyle("-fx-background-color: black;");

    }
    public void playMusic() {
        mediaPlayer.play();
        mediaPlayer.setAutoPlay(true);
        mediaPlayer.setOnEndOfMedia(() -> mediaPlayer.seek(Duration.ZERO));
    }
}