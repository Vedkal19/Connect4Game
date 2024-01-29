package ui;


import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class Fillers extends JComponent {
    public ArrayList<Ball> getBalls() {
        return balls;
    }

    private ArrayList<Ball> balls = new ArrayList<>();
    private ArrayList<Color> colors = new ArrayList<>();

    // private static  Color UFOColor = Color.WHITE; // ball color
    // private static final Color p1 = Color.RED;
    // private static final Color p2 = Color.GREEN;
    // private boolean ufoState;
    // private int radius;
    // private double padding;

    public Fillers() {
        // this.ufoState = true;
        // this.radius = 50;
        // this.padding = 0.1;

        for (int i = 0; i < 42; i++) {
            colors.add(Color.WHITE);
        }
        for (int i = 0; i < 42; i++) {
            balls.add(new Ball(colors,i));
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

    }


}