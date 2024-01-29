package model;

// Represents Alien/ Token that will be dropped into the board
public class Alien {
    public static final Alien EMPTY = new Alien("empty");
    public static final Alien RED = new Alien("red");
    public static final Alien GREEN = new Alien("green");
    private String colour;

    //Constructs am alien
    // EFFECTS: Alien is initialized with a given colour
    public Alien(String colour) {
        this.colour = colour;
    }

    public String getColour() {
        return colour;
    }
}
