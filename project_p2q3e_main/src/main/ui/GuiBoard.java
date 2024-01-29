package ui;

import model.*;
import model.Event;
import persistence.JsonWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class GuiBoard {
    private static final Color p1_color = Color.RED;
    private static final Color p2_color = Color.GREEN;
    private final String jsonSTORE = "./data/game.json";
    private ArrayList<ArrayList<Integer>> myBoard;
    private JsonWriter jsonWriter;
    private ArrayList<Integer> ufo;
    private Connect4Game game;
    private JPanel boardPanel;
    private JPanel gameOverPanel;
    private JFrame boardFrame;
    private JLabel ufoCount;
    private JLabel p1label;
    private JLabel p2label;


    //Creates a GUI board for the game
    public GuiBoard(Connect4Game game) {
        this.game = game;
        this.ufoCount = new JLabel("UFO : 0");
        this.jsonWriter = new JsonWriter(jsonSTORE);
        p1label = new JLabel(game.getPlayer1().getName() + "        ");
        p2label = new JLabel("      " + game.getPlayer2().getName() + "        ");
    }

    //Modifies: board
    //Effects: creates board from scratch
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void fillBoard() {
        myBoard = new ArrayList<>();
        ArrayList<ArrayList<Alien>> b = game.getBoard().getBoard();
        for (int x = 0; x < 7; x++) {
            myBoard.add(new ArrayList<Integer>());
            for (int y = 0; y < 6; y++) {
                // System.out.print(b.get(x).get(y) + " : ");
                if (b.get(x).get(y) == Alien.EMPTY) {
                    myBoard.get(x).add(0);
                } else if (b.get(x).get(y) == Alien.RED) {
                    myBoard.get(x).add(1);
                } else if (b.get(x).get(y) == Alien.GREEN) {
                    myBoard.get(x).add(2);
                }
            }
            // System.out.println();
        }
        for (int x = 0; x < 7; x++) {
            for (int y = 0; y < 6; y++) {
                if (myBoard.get(x).get(y) == 1) {
                    ((Ball) boardPanel.getComponent((7 * y) + x)).changeColor((7 * y) + x, Color.RED);
                } else if (myBoard.get(x).get(y) == 2) {
                    ((Ball) boardPanel.getComponent((7 * y) + x)).changeColor((7 * y) + x, Color.GREEN);
                }
            }
        }
    }

    //Effects: creates and presents the GUI for game
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    void createAndShowGUI() {
        boardFrame = new JFrame("Board");
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6, 7));
        gameOverPanel = new JPanel();
        gameOverPanel.setLayout(null);
        Fillers f = new Fillers();
        for (int i = 0; i < 42; i++) {
            boardPanel.add(f.getBalls().get(i));
        }
        boardFrame.add(boardPanel);
        boardFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent windowEvent) {
                close(EventLog.getInstance());
                System.exit(0);
            }
        });
        fillBoard();
        addLayout(); // method to add the layout
        highlightPlayer(p1label, p2label);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.pack();
        boardFrame.setVisible(true);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    //Effects: plays game until game is over
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private boolean playGame(int key) {
        boolean res = false;
        Player prev = game.getCurrent();
        int count = 0;
        ufo = new ArrayList<>();

        do {
            count++;
            if (key == game.getUfo().getColumn()) {
                res = game.play(game.getUfo().getColumn());
                if (res) {
                    for (int x = 5; x >= 0; x--) {
                        if (myBoard.get(key).get(x) == 0) {
                            myBoard.get(key).set(x, 1);
                            break;
                        }
                    }
                } else {
                    break;
                }
            } else {
                game.moveUFO();
                ufo.add(game.getUfo().getColumn());
                // System.out.println("UFO is above column:" + game.getUfo().getColumn());
            }
        } while (prev == game.getCurrent() && !game.gameOverStatus() && count < 14);
        return res;
    }

    //Effects: Creates a Pane if Match is over
    public void checkMatch() {
        ImageIcon icon = new ImageIcon("src/trophy.png");
        if (game.getPlayer1().getWins() >= game.getMaxGame()
                || game.getPlayer2().getWins() >= game.getMaxGame()) {
            JOptionPane.showMessageDialog(null, "Score : \n"
                    + game.getPlayer1().getName() + ": "
                    + game.getPlayer1().getWins() + " ----- " + game.getPlayer2().getName() + ": "
                    + game.getPlayer2().getWins(), "Match Over!!!", JOptionPane.INFORMATION_MESSAGE, icon);
            close(EventLog.getInstance());
            System.exit(0);
        }
    }

    //Effects: Creates a Pane if Match is over
    public void checkGame() {
        ImageIcon icon = new ImageIcon("src/celebration.png");
        if (game.getBoard().checkTie()) {
            JOptionPane.showMessageDialog(null, "Game is Tied!",
                    "Round Over!!!", JOptionPane.INFORMATION_MESSAGE);
            resetBoard();

        } else if (game.getBoard().checkWin(game.getPlayer1().getAlien())
                || game.getBoard().checkWin(game.getPlayer2().getAlien())) {

            JOptionPane.showMessageDialog(null, "Score : \n"
                    + game.getPlayer1().getName() + ": "
                    + game.getPlayer1().getWins() + " ----- " + game.getPlayer2().getName() + ": "
                    + game.getPlayer2().getWins(), "Game Over!!!", JOptionPane.INFORMATION_MESSAGE, icon);
            resetBoard();
        }
    }

    //Modifies: board
    //Effects: resets entire board
    public void resetBoard() {
        game.reset();
        fillBoard();
        for (int i = 0; i < 42; i++) {
            ((Ball) boardPanel.getComponent(i)).changeColor(i, Color.white);
        }

    }

    //Modifies: board
    //Effects: changes the colour of a certain grid position to reflect the play
    public void updateColor(int key) {
        for (int i = 0; i <= 5; i++) {
            if (myBoard.get(key).get(i) == 1) {
                if (game.getCurrent().getAlien() == Alien.GREEN) {
                    // int pr = (7 * i) + key;
                    // System.out.println(pr);
                    ((Ball) boardPanel.getComponent((7 * i) + key)).changeColor((7 * i) + key, p1_color);
                } else {
                    // int pr = (7 * i) + key;
                    // System.out.println(pr);
                    ((Ball) boardPanel.getComponent((7 * i) + key)).changeColor((7 * i) + key, p2_color);
                }
                break;
            }
        }
    }

    //Modifies: panel
    //Effects: highlights current player
    public void highlightPlayer(JLabel p1label, JLabel p2label) {
        if (game.getCurrent().getAlien() == Alien.RED) {

            p1label.setBackground(Color.YELLOW);
            p2label.setBackground(Color.WHITE);
        } else {
            p2label.setBackground(Color.YELLOW);
            p1label.setBackground(Color.WHITE);
        }
    }

    public void close(EventLog el) {
        for (Event event : el) {
            System.out.println(event);
        }
    }

    //Effects: adds the complete layout of the panel with board
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public void addLayout() {
        Box box = new Box(BoxLayout.X_AXIS);
        JLabel inputLabel = new JLabel("Choose column(0-6) ");
        JTextField inputField = new JTextField();
        JLabel p1color = new JLabel("         ");
        JLabel p2color = new JLabel("         ");
        JLabel dummy = new JLabel("     ");
        p1color.setOpaque(true);
        p1color.setBackground(Color.RED);
        p2color.setOpaque(true);

        p1label.setOpaque(true);
        p2label.setOpaque(true);
        p2color.setBackground(Color.GREEN);

        JButton insert = new JButton("Insert");
        insert.addActionListener(event -> {
            int key = Integer.parseInt(inputField.getText());
            if (playGame(key)) {
                new Thread(() -> {
                    for (int i = 0; i < ufo.size(); i++) {
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                        // Update the label
                        final String labelText = "UFO : " + ufo.get(i);
                        SwingUtilities.invokeLater(() -> ufoCount.setText(labelText));
                    }
                    updateColor(key);
                    checkGame();
                    checkMatch();
                    highlightPlayer(p1label, p2label);

                }).start();

            } else {
                ImageIcon icon = new ImageIcon("src/oops.png");
                JOptionPane.showMessageDialog(null, "Hey " + game.getCurrent().getName()
                        + " !!! Illegal move", "Error", JOptionPane.INFORMATION_MESSAGE, icon);
            }
        });
        JButton save = new JButton("Save");
        save.addActionListener(event -> {
            try {
                jsonWriter.open();
                jsonWriter.write(game);
                jsonWriter.close();
                JOptionPane.showMessageDialog(null, "Thank You!",
                        "Game saved!!!", JOptionPane.NO_OPTION);
                close(EventLog.getInstance());
                System.exit(0);
                // boardFrame.dispose();

            } catch (FileNotFoundException e) {
                // System.out.println("Unable to write to file: " + jsonSTORE);
            }
        });
        JButton reset = new JButton("Reset");
        reset.addActionListener(event -> {
            resetBoard();
            resetBoard();
        });
        box.add(inputLabel);
        box.add(inputField);
        box.add(insert);
        box.add(save);
        box.add(ufoCount);
        box.add(reset);
        box.add(p1label);
        box.add(p1color);
        box.add(p2label);
        box.add(p2color);
        box.add(dummy);
        boardFrame.add(box, BorderLayout.NORTH);
    }
}