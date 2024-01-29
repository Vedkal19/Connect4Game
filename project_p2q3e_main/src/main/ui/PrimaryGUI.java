package ui;

import javax.swing.*;

public class PrimaryGUI extends JFrame {

    //Creates a preliminary panel to the game
    public PrimaryGUI() {
        setTitle("Welcome to Connect UF4!!!");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel and add it to the frame
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // create a label
        JLabel label = new JLabel("New Game or Load Game:");
        panel.add(label);

        // create a dropdown list
        String[] items = {"New Game", "Load Game"};
        JComboBox<String> dropdown = new JComboBox<>(items);
        panel.add(dropdown);
        JButton next = new JButton("Next");
        next.addActionListener(event -> {
            if (dropdown.getSelectedIndex() == 1) {
                GuiGame obj = new GuiGame();
                this.setVisible(false);

                obj.start();
            } else {
                SecondaryGUI obj = new SecondaryGUI();
                this.setVisible(false);
                obj.setVisible(true);
            }
        });
        panel.add(next);
    }

    //Effects: starts the game procedure
    public static void main(String[] args) {
        PrimaryGUI frame = new PrimaryGUI();
        frame.setVisible(true);
    }
}
