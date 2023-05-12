// Import necessary classes for the pakman class
import javax.swing.JFrame;

// This class is used to run the Model class to start the Pakman game
public class pakman extends JFrame{
    // Add a new Model class
    public pakman() {
        add(new Model());
    }

    public static void main(String[] args) {
        // Initialize the pakman class
        pakman pak = new pakman();
        // Display the Game
        pak.setVisible(true);
        pak.setTitle("Pakman");
        pak.setSize(380,420);
        pak.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pak.setLocationRelativeTo(null);
    }
}

