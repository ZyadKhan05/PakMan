// Import all the classes
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

// This class contains all the Javax swing and JavaFX required for the game to run
public class Model extends JPanel implements ActionListener {
    // Initialization of variables used in the class
    public static boolean dead = false;
    private Dimension d;
    private final Font smallFont = new Font("Comic Sans MS", Font.BOLD, 14);
    private boolean inGame = false;
    private boolean dying = false;
    private final int BLOCK_SIZE = 24;
    private final int N_BLOCKS = 15;
    private final int SCREEN_SIZE = N_BLOCKS * BLOCK_SIZE;
    private final int MAX_GHOSTS = 12;
    private final int MAX_MERT = 500;
    private final int PACMAN_SPEED = 6;
    private int N_GHOSTS = 3;
    private int N_MERTS = 2;
    private int lives, score;
    private int[] dx, dy;
    private int[] ghost_x, ghost_y, ghost_dx, ghost_dy, ghostSpeed;
    private int[] mert_x, mert_y, mert_dx, mert_dy, mertSpeed;

    private Image heart, ghost, mert;
    private Image up, down, left, right;

    private int pacman_x, pacman_y, pacmand_x, pacmand_y;
    private int req_dx, req_dy;

    pause pauses = new pause();
    boolean pauseGame;
    // Media and MediaPlayer
    Media media;
    MediaPlayer player;
    PauseTransition timerPause;
    Alert alert;




    // Creates the Game board. 0's represent blue spaces, 1=left border, 2=top border, 4=right border
    //8=bottom border, and 16=white dots & traversable spaces.
    private final short[] levelData = {
            19, 26, 26, 26, 18, 18, 18, 18, 18, 18, 18, 18, 18, 18, 22,
            21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 16, 24, 24, 16, 20,
            21, 0, 0, 0, 17, 16, 16, 16, 16, 16, 20, 0, 0, 17, 20,
            21, 0, 0, 0, 17, 16, 16, 24, 16, 16, 20, 0, 19, 16, 20,
            17, 18, 18, 18, 16, 16, 20, 0, 17, 16, 16, 18, 16, 16, 20,
            17, 16, 16, 16, 16, 16, 20, 0, 17, 16, 16, 16, 16, 24, 20,
            25, 16, 16, 16, 24, 24, 28, 0, 25, 24, 24, 16, 20, 0, 21,
            0, 17, 16, 20,  0, 0, 0, 0, 0, 0, 0, 17, 20,       0, 21,
            0, 17, 16, 16,  18, 18, 22, 0, 19, 18, 18, 16, 20, 0, 21,
            0, 17, 16, 16,  16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
            0, 17, 16, 16,  16, 16, 20, 0, 17, 16, 16, 16, 20, 0, 21,
            0, 17, 16, 16, 16, 16, 16, 18, 16, 16, 16, 16, 20, 0, 21,
            0, 17, 16, 16, 16, 16, 16, 16, 16, 16, 16, 16, 20, 0, 21,
            0, 25, 24, 24, 24, 24, 24, 24, 24, 24, 16, 16, 16, 18, 20,
            0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 25, 24, 24,          24, 28
    };

    // Speed options
    private final int[] validSpeeds = {1, 2, 3, 4, 6, 8};
    private final int maxSpeed = 6;
    private int currentSpeed = 3;
    private short[] screenData;
    private Timer timer;

    //constructor for Model class, initializes game.
    public Model() {
        loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        initGame();
    }

    // Initialize and load all images used in the game
    private void loadImages() {
        down = new ImageIcon("pacman/Resources/Images/down.gif").getImage();
        up = new ImageIcon("pacman/Resources/Images/up.gif").getImage();
        left = new ImageIcon("pacman/Resources/Images/left.gif").getImage();
        right = new ImageIcon("pacman/Resources/Images/right.gif").getImage();
        heart = new ImageIcon("pacman/Resources/Images/heart.png").getImage();
        ghost = new ImageIcon("pacman/Resources/Images/ghost.gif").getImage();
        mert= new ImageIcon("pacman/Resources/Images/ghost.png").getImage();

    }
    // Method to initialize all the variables with their correct values
    private void initVariables() {
        screenData = new short[N_BLOCKS * N_BLOCKS];
        d = new Dimension(400, 400);
        ghost_x = new int[MAX_GHOSTS];
        ghost_dx = new int[MAX_GHOSTS];
        ghost_y = new int[MAX_GHOSTS];
        ghost_dy = new int[MAX_GHOSTS];
        ghostSpeed = new int[MAX_GHOSTS];
        mert_x = new int[MAX_MERT];
        mert_dx = new int[MAX_MERT];
        mert_y = new int[MAX_MERT];
        mert_dy = new int[MAX_MERT];
        mertSpeed = new int[MAX_MERT];
        dx = new int[4];
        dy = new int[4];
        //how often the game is redrawn, delay is in milliseconds
        timer = new Timer(100, this);
        timer.start();
    }

    // Check if the player is dead, if not keep on playing
    private void playGame(Graphics2D g2d) {
        if (dying) {
            //call death method
            death();
        }
        else {
            movePacman();
            drawPacman(g2d);
            moveGhosts(g2d);
            moveMerts(g2d);
        }
    }

    // Method to show the intro screen of the game
    private void showIntroScreen(Graphics2D g2d) {
        String start = "Press SPACE to play";
        g2d.setColor(Color.yellow);
        g2d.drawString(start, (SCREEN_SIZE)/4, 150);
    }

    // Method to check if the player wins
    private void gameWon(){
        if (score == 176) {
            // Reset all variables to zero or false
            score = 0;
            N_MERTS = 0;
            N_GHOSTS = 0;
            inGame = false;
            // Stop game music
            player.stop();
            timerPause = new PauseTransition(Duration.seconds(3));
            // Run the win scene
            Platform.runLater(() -> {
                // Create a new instance of the "Stage" class
                Stage stage = new Stage();
                main main = new main();
                win win = new win();
                // Call the start method on the main JavaFX class
                try {
                    // Set the stage to the win screen
                    main.start(stage);
                    win.setWin();
                    stage.setScene(win.winScene);
                    stage.setMaximized(true);
                    main.stopMusic();
                    timerPause.play();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Death screen button actions
                win.quitButton.setOnAction(event -> {
                    stage.close();
                    System.exit(0);
                });
                win.restartButton.setOnAction(event -> {
                    win.mediaPlayer.stop();
                    stage.close();
                    initGame();
                });
            });
            timerPause.setOnFinished(e -> {
                Platform.runLater(() -> {
                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Computer Message");
                    alert.setHeaderText(null);
                    alert.setContentText("Mert will takeover and destroy your computer in 10 seconds!");
                    alert.showAndWait();
                });
            });
        }
    }

    // Method to check if the player loses
    private void death() {
        lives--;
        if (lives == 0) {
            inGame = false;
            player.stop();
            // Launch the JavaFX application
            Platform.runLater(() -> {
                // Create a new instance of the "Stage" class
                Stage stage = new Stage();
                main main = new main();
                death death = new death();
                // Call the start method on the main JavaFX class
                try {
                    // Set the stage to the death screen
                    main.start(stage);
                    death.setDeath();
                    stage.setScene(death.deathScene);
                    stage.setMaximized(true);
                    main.stopMusic();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Death screen button actions
                death.quitButton.setOnAction(event -> {
                    stage.close();
                    System.exit(0);
                });
                death.restartButton.setOnAction(event -> {
                    death.mediaPlayer.stop();
                    stage.close();
                    initGame();
                });
            });
        }
        spawnghostandMert();
    }

    // Method to update and draw the player's score
    private void drawScore(Graphics2D g) {
        g.setFont(smallFont);
        g.setColor(new Color(5, 181, 79));
        String s = "Score: " + score;
        g.drawString(s, SCREEN_SIZE / 2 + 96, SCREEN_SIZE + 16);
        for (int i = 0; i < lives; i++) {
            g.drawImage(heart, i * 28 + 8, SCREEN_SIZE + 1, this);
        }
    }

    // Method to randomly move each ghost
    private void moveGhosts(Graphics2D g2d) {
        int pos;
        int count;
        for (int i = 0; i < N_GHOSTS; i++) {
            //logic for ghost to only continue if they have finished moving 1 whole square
            if (ghost_x[i] % BLOCK_SIZE == 0 && ghost_y[i] % BLOCK_SIZE == 0) {
                //determines the position of the ghosts
                pos = ghost_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (ghost_y[i] / BLOCK_SIZE);
                count = 0;

                //Logic to force ghost to finish traveling through hallway/tunnel
                //if movement is random in narrow tunnel/hallway the ghost might get trapped
                if ((screenData[pos] & 1) == 0 && ghost_dx[i] != 1) {
                    dx[count] = -1;
                    dy[count] = 0;
                    count++;
                }
                if ((screenData[pos] & 2) == 0 && ghost_dy[i] != 1) {
                    dx[count] = 0;
                    dy[count] = -1;
                    count++;
                }
                if ((screenData[pos] & 4) == 0 && ghost_dx[i] != -1) {
                    dx[count] = 1;
                    dy[count] = 0;
                    count++;
                }
                if ((screenData[pos] & 8) == 0 && ghost_dy[i] != -1) {
                    dx[count] = 0;
                    dy[count] = 1;
                    count++;
                }
                if (count == 0) {
                    if ((screenData[pos] & 15) == 15) {
                        ghost_dx[i] = 0;
                        ghost_dy[i] = 0;
                    } else {
                        ghost_dx[i] = -ghost_dx[i];
                        ghost_dy[i] = -ghost_dy[i];
                    }
                } else {
                    count = (int) (Math.random() * count);
                    if (count > 3) {
                        count = 3;
                    }
                    ghost_dx[i] = dx[count];
                    ghost_dy[i] = dy[count];
                }
            }
            ghost_x[i] = ghost_x[i] + (ghost_dx[i] * ghostSpeed[i]);
            ghost_y[i] = ghost_y[i] + (ghost_dy[i] * ghostSpeed[i]);
            //redraw the ghosts position
            drawghost(g2d, ghost_x[i] + 1, ghost_y[i] + 1);

            //detects collision with pakman
            if (pacman_x > (ghost_x[i] - 12) && pacman_x < (ghost_x[i] + 12)
                    && pacman_y > (ghost_y[i] - 12) && pacman_y < (ghost_y[i] + 12)
                    && inGame) {
                dying = true;
            }
        }
    }

    // Method to randomly move the mert ghost. Same logic as moveGhost logic
    private void moveMerts(Graphics2D g2d) {
        int posM;
        int countM;
        for (int i = 0; i < N_MERTS; i++) {
            if (mert_x[i] % BLOCK_SIZE == 0 && mert_y[i] % BLOCK_SIZE == 0) {
                posM = mert_x[i] / BLOCK_SIZE + N_BLOCKS * (int) (mert_y[i] / BLOCK_SIZE);
                countM = 0;
                if ((screenData[posM] & 1) == 0 && mert_dx[i] != 1) {
                    dx[countM] = -1;
                    dy[countM] = 0;
                    countM++;
                }
                if ((screenData[posM] & 2) == 0 && mert_dy[i] != 1) {
                    dx[countM] = 0;
                    dy[countM] = -1;
                    countM++;
                }
                if ((screenData[posM] & 4) == 0 && mert_dx[i] != -1) {
                    dx[countM] = 1;
                    dy[countM] = 0;
                    countM++;
                }
                if ((screenData[posM] & 8) == 0 && mert_dy[i] != -1) {
                    dx[countM] = 0;
                    dy[countM] = 1;
                    countM++;
                }
                if (countM == 0) {
                    if ((screenData[posM] & 15) == 15) {
                        mert_dx[i] = 0;
                        mert_dy[i] = 0;
                    } else {
                        mert_dx[i] = -mert_dx[i];
                        mert_dy[i] = -mert_dy[i];
                    }
                } else {
                    countM = (int) (Math.random() * countM);
                    if (countM > 3) {
                        countM = 3;
                    }
                    mert_dx[i] = dx[countM];
                    mert_dy[i] = dy[countM];
                }
            }

            mert_x[i] = mert_x[i] + (mert_dx[i] * mertSpeed[i]);
            mert_y[i] = mert_y[i] + (mert_dy[i] * mertSpeed[i]);
            drawMert(g2d, mert_x[i] + 1, mert_y[i] + 1);
            if (pacman_x > (mert_x[i] - 12) && pacman_x < (mert_x[i] + 12)
                    && pacman_y > (mert_y[i] - 12) && pacman_y < (mert_y[i] + 12)
                    && inGame) {
                dying = true;
            }
        }
    }

    // Method to draw the image of the ghost
    private void drawghost(Graphics2D g2d, int x, int y) {
        g2d.drawImage(ghost, x, y, this);
    }

    // Method to draw the images of the Merts
    private void drawMert(Graphics2D g2d, int x, int y) {
        g2d.drawImage(mert, x, y, this);
    }

    // Method to control pacman's movement
    private void movePacman() {
        int pos;
        short ch;
        if (pacman_x % BLOCK_SIZE == 0 && pacman_y % BLOCK_SIZE == 0) {
            pos = pacman_x / BLOCK_SIZE + N_BLOCKS * (int) (pacman_y / BLOCK_SIZE);
            ch = screenData[pos];
            //if pacman moves to position with pellet, it removes pellet and
            //increments score
            if ((ch & 16) != 0) {
                screenData[pos] = (short) (ch & 15);
                score++;
            }
            //detects pacman running into border. Stops pacman movement
            if (req_dx != 0 || req_dy != 0) {
                if (!((req_dx == -1 && req_dy == 0 && (ch & 1) != 0)
                        || (req_dx == 1 && req_dy == 0 && (ch & 4) != 0)
                        || (req_dx == 0 && req_dy == -1 && (ch & 2) != 0)
                        || (req_dx == 0 && req_dy == 1 && (ch & 8) != 0))) {
                    pacmand_x = req_dx;
                    pacmand_y = req_dy;
                }
            }
            // Check for standstill
            if ((pacmand_x == -1 && pacmand_y == 0 && (ch & 1) != 0)
                    || (pacmand_x == 1 && pacmand_y == 0 && (ch & 4) != 0)
                    || (pacmand_x == 0 && pacmand_y == -1 && (ch & 2) != 0)
                    || (pacmand_x == 0 && pacmand_y == 1 && (ch & 8) != 0)) {
                pacmand_x = 0;
                pacmand_y = 0;
            }
        }
        pacman_x = pacman_x + PACMAN_SPEED * pacmand_x;
        pacman_y = pacman_y + PACMAN_SPEED * pacmand_y;
    }

    // Draw pacman image with respect to players direction
    private void drawPacman(Graphics2D g2d) {
        if (req_dx == -1) {
            g2d.drawImage(left, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dx == 1) {
            g2d.drawImage(right, pacman_x + 1, pacman_y + 1, this);
        } else if (req_dy == -1) {
            g2d.drawImage(up, pacman_x + 1, pacman_y + 1, this);
        } else {
            g2d.drawImage(down, pacman_x + 1, pacman_y + 1, this);
        }
    }

    // Draw the maze out of the nums in the screenData[] array
    private void drawMaze(Graphics2D g2d) {
        short i = 0;
        int x, y;
        for (y = 0; y < SCREEN_SIZE; y += BLOCK_SIZE) {
            for (x = 0; x < SCREEN_SIZE; x += BLOCK_SIZE) {
                g2d.setColor(new Color(0,72,251));
                g2d.setStroke(new BasicStroke(5));

                //draw blue block
                if ((levelData[i] == 0)) {
                    g2d.fillRect(x, y, BLOCK_SIZE, BLOCK_SIZE);
                }
                //draw left border
                if ((screenData[i] & 1) != 0) {
                    g2d.drawLine(x, y, x, y + BLOCK_SIZE - 1);
                }
                //draw top border
                if ((screenData[i] & 2) != 0) {
                    g2d.drawLine(x, y, x + BLOCK_SIZE - 1, y);
                }
                //draw right border
                if ((screenData[i] & 4) != 0) {
                    g2d.drawLine(x + BLOCK_SIZE - 1, y, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                //draw bottom border
                if ((screenData[i] & 8) != 0) {
                    g2d.drawLine(x, y + BLOCK_SIZE - 1, x + BLOCK_SIZE - 1,
                            y + BLOCK_SIZE - 1);
                }
                //draw traversable space & pellet
                if ((screenData[i] & 16) != 0) {
                    g2d.setColor(new Color(255,255,255));
                    g2d.fillOval(x + 10, y + 10, 6, 6);
                }
                i++;
            }
        }
    }
    //initializes the game. Sets the lives, score, number of enemies, and speed
    private void initGame() {
        lives = 3;
        score = 0;
        initLevel();
        N_GHOSTS = 1;
        N_MERTS= 4;
        currentSpeed = 3;

    }
    // Secret (Not so secret) Easter Egg
    // If player selects M instead of SPACE on the game screen, the Mertageddon begins
    private void initMertageddon () {
        lives = 8;
        score = 50;
        N_GHOSTS = 0;
        N_MERTS= 150;
        initLevel();
        currentSpeed = 1;
    }

    //initializes the level, iterates through level data
    private void initLevel() {
        int i;
        for (i = 0; i < N_BLOCKS * N_BLOCKS; i++) {
            screenData[i] = levelData[i];
        }
        spawnghostandMert();
    }

    //Method that decides where the npcs spawn and randomizes their speeds
    private void spawnghostandMert() {
        int dx = 1;
        int random;
        //Spawns ghost and sets speed
        for (int i = 0; i < N_GHOSTS; i++) {
            ghost_y[i] = 4 * BLOCK_SIZE; //start position
            ghost_x[i] = 4 * BLOCK_SIZE;
            ghost_dy[i] = 0;
            ghost_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));
            if (random > currentSpeed) {
                random = currentSpeed;
            }
            ghostSpeed[i] = validSpeeds[random];
        }
        //Spawns merts and sets speed
        for (int i = 0; i < N_MERTS; i++) {
            mert_y[i] = 4 * BLOCK_SIZE; //start position
            mert_x[i] = 4 * BLOCK_SIZE;
            mert_dy[i] = 0;
            mert_dx[i] = dx;
            dx = -dx;
            random = (int) (Math.random() * (currentSpeed + 1));
            if (random > currentSpeed) {
                random = currentSpeed;
            }
            mertSpeed[i] = validSpeeds[random];
        }
        pacman_x = 7 * BLOCK_SIZE;  //start position
        pacman_y = 11 * BLOCK_SIZE;
        pacmand_x = 0;	//reset direction move
        pacmand_y = 0;
        req_dx = 0;		// reset direction controls
        req_dy = 0;
        dying = false;
    }


    //method to establish paint class used to draw everything
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height);
        drawMaze(g2d);
        drawScore(g2d);
        gameWon();
        if (inGame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }

    // Controls for Pak
    class TAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            if (inGame) {
                // On Left Arrow/A Key pressed
                if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
                    req_dx = -1;
                    req_dy = 0;
                // On Right Arrow/D Key pressed
                } else if (key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {
                    req_dx = 1;
                    req_dy = 0;
                // On Up Arrow/W Key pressed
                } else if (key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {
                    req_dx = 0;
                    req_dy = -1;
                // On Down Arrow/S Key Pressed
                } else if (key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {
                    req_dx = 0;
                    req_dy = 1;
                // On Escape Key Pressed
                } else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    pauseGame = true;
                    player.stop();
                    Platform.runLater(() -> {
                        // Create a new instance of the "Stage" class
                        Stage stage = new Stage();
                        main main = new main();
                        menu menu = new menu();

                        // Call the start method on the main JavaFX class
                        try {
                            main.start(stage);
                            pauses.setPause();
                            stage.setScene(pauses.pauseScene);
                            stage.setMaximized(true);
                        } catch (Exception x) {
                            x.printStackTrace();
                        }
                        // Pause menu events
                        // Quit button closes application
                        pauses.quitButton.setOnAction(event -> {
                            stage.close();
                            System.exit(0);
                        });
                        pauses.resumeButton.setOnAction(event -> {
                            stage.close();
                            menu.mediaPlayer = new MediaPlayer(media);
                            menu.mediaPlayer.play();
                        });;
                    });

                }
            } else {
                // If Space Key is pressed on the game screen, game starts with the music
                if (key == KeyEvent.VK_SPACE) {
                    inGame = true;
                    media = new Media(new File("PacMan/Resources/music/mertWaka.wav").toURI().toString());
                    player = new MediaPlayer(media);
                    player.setAutoPlay(true);
                    player.setOnEndOfMedia(() -> player.seek(Duration.seconds(5)));
                    initGame();
                }
                // If M is pressed on the game screen, the game will have a secret game mode called the Mertageddon
                if (key == KeyEvent.VK_M) {
                    inGame = true;
                    media = new Media(new File("PacMan/Resources/music/Mertageddon.wav").toURI().toString());
                    player = new MediaPlayer(media);
                    player.setAutoPlay(true);
                    player.setOnEndOfMedia(() -> player.seek(Duration.seconds(5)));
                    initMertageddon();
                }
            }
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }
}