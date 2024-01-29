package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

public class Ball extends JComponent {
    ArrayList<Color> colors;
    int count = 0;
    // private boolean ufoState;
    private int radius;
    private double padding;

    //Creates a ball (Alien)
    public Ball(ArrayList<Color> colors, int i) {
        // this.ufoState = true;
        this.radius = 50;
        this.padding = 0.1;
        this.colors = colors;
        this.count = i;
    }

    //Modifies: this
    //Effects: creates balls of specified colour and size
    @Override
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

        g2.setColor(Color.gray);
        Rectangle rect = new Rectangle(0, 0, getWidth() - 1, getHeight() - 1);
        g2.fill(rect);

        for (int i = 0; i < 42; i++) {
            if (count == i) {
                g2.setColor(colors.get(i)); // To fill the circle with white
            }
        }
        g2.fill(circle);
        g2.draw(circle);
    }

    //Modifies: this
    //Effects: returns preferred size of balls
    @Override
    public Dimension getPreferredSize() {
        int size = 2 * (radius);
        return new Dimension(size + 1, size + 1);
    }

    //Modifies: this
    //Effects: changes the colour of the ball
    public void changeColor(int i, Color color) {
        colors.set(i, color);
        this.repaint();
    }
}


