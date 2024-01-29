package model;

import java.util.ArrayList;



// Represents the Connect 4 board
public class Board {
    EventLog log;
    public static final int ROWS = 6;
    public static final int COLUMNS = 7;

    public ArrayList<ArrayList<Alien>> getBoard() {
        return board;
    }

    private ArrayList<ArrayList<Alien>> board;

    //EFFECTS: Constructs the Connect 4 board of set number of columns and rows
    public Board() {
        log = EventLog.getInstance();
        board = new ArrayList<ArrayList<Alien>>();
        for (int x = 0; x < COLUMNS; x++) {
            board.add(new ArrayList<Alien>());
            for (int y = 0; y < ROWS; y++) {
                board.get(x).add(Alien.EMPTY);
            }
        }
        log.logEvent(new Event("Board has been created"));
    }

    //REQUIRES: c and r must be within the preset ranges for columns and rows
    //EFFECTS: returns the Alien at the given position in the board
    public Alien getCellContent(int c, int r) {
        return board.get(c).get(r);
    }

    //MODIFIES: this
    //EFFECTS: drops alien into lowest point possible in given column if valid else returns false
    public boolean dropAlien(int column, Alien alien) {
        if (column < 0 || COLUMNS <= column) {
            log.logEvent(new Event("Index out of bound(0-6) : " + column));
            return false;
        } else {
            for (int x = ROWS - 1; x >= 0; x--) {
                if (board.get(column).get(x) == Alien.EMPTY) {
                    board.get(column).set(x, alien);
                    return true;
                }
            }
        }
        log.logEvent(new Event("Column full: " + column));
        return false;
    }

    //REQUIRES: Alien given should valid
    //EFFECTS: Returns true if there is a connect 4 horizontally
    public boolean checkHor(Alien colour) {
        for (int x = 0; x < ROWS; x++) {
            for (int y = 0; y < COLUMNS - 3; y++) {
                if (board.get(y).get(x) == colour
                        && board.get(y + 1).get(x) == colour
                        && board.get(y + 2).get(x) == colour
                        && board.get(y + 3).get(x) == colour) {
                    log.logEvent(new Event("Horizontal match found for color: " + colour.getColour()));
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: Alien given should valid
    //EFFECTS: Returns true if there is a connect 4 vertically
    public boolean checkVert(Alien colour) {
        for (int x = 0; x < COLUMNS; x++) {
            for (int y = 0; y < ROWS - 3; y++) {
                if (board.get(x).get(y) == colour
                        && board.get(x).get(y + 1) == colour
                        && board.get(x).get(y + 2) == colour
                        && board.get(x).get(y + 3) == colour) {
                    log.logEvent(new Event("Vertical match found for color: " + colour.getColour()));
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: Alien given should valid
    //EFFECTS: Returns true if there is a connect 4 diagonally from top right to bottom left
    public boolean checkDiag1(Alien colour) {
        // Top right to Bottom left
        for (int x = COLUMNS - 1; x >= 3; x--) {
            for (int y = 0; y < ROWS - 3; y++) {
                if (board.get(x).get(y) == colour
                        && board.get(x - 1).get(y + 1) == colour
                        && board.get(x - 2).get(y + 2) == colour
                        && board.get(x - 3).get(y + 3) == colour) {
                    log.logEvent(new Event("Diagonal match found for color: " + colour.getColour()));
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: Alien given should valid
    //EFFECTS: Returns true if there is a connect 4 diagonally from top left to bottom right
    public boolean checkDiag2(Alien colour) {
        // Top left to Bottom Right
        for (int x = 0; x < COLUMNS - 3; x++) {
            for (int y = 0; y < ROWS - 3; y++) {
                if (board.get(x).get(y) == colour
                        && board.get(x + 1).get(y + 1) == colour
                        && board.get(x + 2).get(y + 2) == colour
                        && board.get(x + 3).get(y + 3) == colour) {
                    log.logEvent(new Event("Diagonal match found for color: " + colour.getColour()));
                    return true;
                }
            }
        }
        return false;
    }

    //REQUIRES: Alien given should valid
    //EFFECTS: Returns true if there is a connect 4 anywhere using methods previously given
    public boolean checkWin(Alien colour) {
        return (checkHor(colour) || checkVert(colour) || checkDiag1(colour) || checkDiag2(colour));
    }

    //EFFECTS: Returns true if board is full and no connect 4 completed
    public boolean checkTie() {
        for (int x = 0; x < COLUMNS; x++) {
            if (board.get(x).get(0) == Alien.EMPTY) {
                return false;
            }
        }
        return true;
    }
}
