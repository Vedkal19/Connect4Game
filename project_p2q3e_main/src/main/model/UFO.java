package model;

//concept adapted from 210 Space Invaders Refactored
//Represents a UFO
public class UFO {
    protected int column;
    protected int height;
    protected int direction;

    //Constructs a UFO
    //EFFECTS: UFO located above column c on board and at a specific height with direction 1
    public UFO(int c, int height) {
        this.column = c;
        this.height = height;
        direction = 1;
    }

    //MODIFIES: this
    //EFFECTS: if UFO is outside the boundary of the board, direction will switch and UFO will be placed at boundary
    protected void boundary() {
        if (column < 0) {
            column = 0;
            direction = 1;
        } else if (column >= Board.COLUMNS) {
            column = Board.COLUMNS - 1;
            direction = -1;
        }
    }

    public int getColumn() {
        return column;
    }

    public int getHeight() {
        return height;
    }

    public int getDirection() {
        return direction;
    }

    //MODIFIES: this
    //EFFECTS: changes the column above which UFO is by direction. Checks whether this takes it outside boundaries
    public void move() {
        column += direction;
        boundary();
    }
}
