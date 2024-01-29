package ui;


import javax.swing.*;


public class SecondaryGUI extends JFrame {

    private JTextField name1Field;
    private JTextField name2Field;

    //Creates the actual board game gui
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public SecondaryGUI() {
        setTitle("Players");
        setSize(300, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create a panel and add it to the frame
        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // create the first text field and add it to the panel
        JLabel nameLabel1 = new JLabel("Player 1:");
        panel.add(nameLabel1);
        name1Field = new JTextField(20);
        panel.add(name1Field);

        // create the second text field and add it to the panel
        JLabel nameLabel2 = new JLabel("Player 2:");
        panel.add(nameLabel2);
        name2Field = new JTextField(20);
        panel.add(name2Field);

        JLabel gameCount = new JLabel("First to:");
        panel.add(gameCount);
        JTextField gameCountField = new JTextField(20);
        panel.add(gameCountField);

        JButton nextButton = new JButton("Next");
        panel.add(nextButton);
        nextButton.addActionListener(event -> {
            String name1 = name1Field.getText();
            String name2 = name2Field.getText();
            int count = Integer.parseInt(gameCountField.getText());
            GuiGame obj = new GuiGame(name1, name2, count);
            this.setVisible(false);
            obj.start();
        });
    }


}
