import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

class game {
    Label playLabel = new Label();

    HBox playHbox;

    menu gameMenu = new menu();

    Scene playScene;

    public void setGame() {
        playLabel.setText("Welcome to the Game");
        playHbox = new HBox(10, playLabel);
        playHbox.setAlignment(Pos.CENTER);
        playScene = new Scene(playHbox);
    }


}
