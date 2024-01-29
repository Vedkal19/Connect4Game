package ui;

import java.awt.*;

import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class GuiMain extends JComponent {

    //    private static Color UFOColor = Color.LIGHT_GRAY;
    private static Color UFOColor = Color.WHITE; // ball color
    private static Color faceColor = Color.BLUE;
//    private static Color mouseOverColor = new Color(255, 255, 127);

    // private String label;

    private boolean ufoState;

    private int radius;
    private double padding;

   

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }

    private static void createAndShowGUI() {
        JFrame boardFrame = new JFrame("Board");
        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(6, 7));

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                boardPanel.add(new GuiMain());
            }

        }

        boardFrame.add(boardPanel);
        addLayout(boardFrame);
        boardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        boardFrame.pack();
        boardFrame.setVisible(true);
    }

    public GuiMain() {
        this.ufoState = true;
        this.radius = 50;
        this.padding = 0.1;
    }


    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2 = (Graphics2D) g;
        Dimension size = getSize();
        radius = (int) (
                (Math.min(size.width, size.height) / 2)
                        - (radius * padding));

        Ellipse2D circle = new Ellipse2D.Double(
                (size.width / 2) - radius,
                (size.height / 2) - radius,
                radius * 2, radius * 2);

        if (ufoState) {
            g2.setColor(Color.gray); // to fill the circle
//            g.setColor(faceColor);
        } else {
            g2.setColor(faceColor);
        }

        Rectangle rect = new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
        g2.fill(rect);
        g2.setColor(UFOColor); // To fill the circle with white
        g2.fill(circle);
        g2.draw(circle);

    }

    public Dimension getPreferredSize() {
        int size = 2 * (radius);
        return new Dimension(size, size);
    }

    public static void addLayout(JFrame frame) {
//         ActionListener actionQuit = new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent event) {
// //                System.exit(0);
//             }
//         };

        Box box = new Box(BoxLayout.X_AXIS);
        frame.add(box, BorderLayout.NORTH);
        JLabel label = new JLabel("Input");
        JTextField input = new JTextField();
        box.add(Box.createHorizontalGlue());
        box.add(label);
        box.add(input);
    }
}