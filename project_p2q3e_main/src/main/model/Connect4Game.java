package model;

import org.json.JSONArray;
import org.json.JSONObject;

import persistence.Writable;

import static model.Board.COLUMNS;
import static model.Board.ROWS;

//Represents a Connect 4 game
public class Connect4Game implements Writable {
    EventLog log;
    private final Player player1;
    private final Player player2;
    private Board board;
    private Player current;
    private final UFO ufo;
    private boolean gameOver;
    private int maxGames;

    //Constructor
    //EFFECTS: sets up the game
    public Connect4Game(String name1, String name2) {
        log = EventLog.getInstance();
        this.player1 = new Player(name1, Alien.RED);
        log.logEvent(new Event("Player " + player1.getName() + " has been created"));
        this.player2 = new Player(name2, Alien.GREEN);
        log.logEvent(new Event("Player " + player2.getName() + " has been created"));
        this.board = new Board();
        this.current = player1;
        log.logEvent(new Event("Current player  has been set to " + player1.getName()));
        this.gameOver = false;
        this.ufo = new UFO(0, 5);
        
    }

    public int getMaxGame() {
        return maxGames;
    }

    public void setMaxGames(int i) {
        maxGames = i;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Board getBoard() {
        return board;
    }

    public UFO getUfo() {
        return ufo;
    }

    public Player getCurrent() {
        return current;
    }

    //REQUIRES: current cannot be both player 1 and player 2 at the same time
    //MODIFIES: current
    //EFFECTS: switches the current player between players 1 and 2
    public void switchPlayer() {
        if (current == player1) {
            current = player2;
            log.logEvent(new Event("Current player  has been set to " + player2.getName()));
        } else {
            current = player1;
            log.logEvent(new Event("Current player has been set to " + player1.getName()));
        }
    }

    //MODIFIES: Board, Player, gameOver
    //EFFECTS: Drops valid alien into given column then checks for the end of the game or switches current player
    public boolean play(int column) {
        boolean valid = board.dropAlien(column, current.getAlien());
        if (valid) {
            log.logEvent(new Event("Alien has been dropped successfully, column: " + column));
            if (board.checkWin(current.getAlien())) {
                checkWinMethod();
                log.logEvent(new Event(player1.getName() + "Win:" + player1.getWins() + ", " + "Loss:"
                        + player1.getLosses()));
                log.logEvent(new Event(player2.getName() + "Win:" + player2.getWins() + ", " + "Loss:"
                        + player2.getLosses()));
                gameOver = true;
            } else if (board.checkTie()) {
                log.logEvent(new Event("Game tie"));
                gameOver = true;
            }
            switchPlayer();
        }
        return valid;
    }

    //Modifies: player
    //Effects: adds win and losses to the column for player
    private void checkWinMethod() {
        if (current == player1) {
            player1.addWin();
            player2.addLoss();
            log.logEvent(new Event(player1.getName() + " wins the game"));
        } else {
            player2.addWin();
            player1.addLoss();
            log.logEvent(new Event(player2.getName() + " wins the game"));
        }
    }

    //MODIFIES: UFO
    //EFFECTS: moves UFO by direction number of columns
    public void moveUFO() {
        ufo.move();
    }

    //MODIFIES: Board, gameOver
    //EFFECTS: resets the board to empty and makes sure game is not over
    public void reset() {
        log.logEvent(new Event("Board has been reset"));
        board = new Board();
        gameOver = false;
    }

    public boolean gameOverStatus() {
        return gameOver;
    }

    public JSONObject toJson() {
        JSONObject game = new JSONObject();
        game.put("player1", player1.toJson());
        game.put("player2", player2.toJson());
        game.put("current", current.getName());
        game.put("board", coloursToBoard());
        game.put("max", maxGames);
        return game;
    }

    private JSONArray coloursToBoard() {
        JSONArray jsonArray = new JSONArray(new JSONArray());
        for (int y = ROWS - 1; y >= 0; y--) {
            JSONArray row = new JSONArray();
            for (int x = 0; x < COLUMNS; x++) {
                row.put(getBoard().getCellContent(x, y).getColour());
            }
            jsonArray.put(row);
        }
        return jsonArray;
    }
}


