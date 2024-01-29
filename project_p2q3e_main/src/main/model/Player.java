package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Player playing the game
public class Player implements Writable {
    private String name;
    private int losses;
    private int wins;
    private Alien alien;

    //Constructs a player
    //EFFECTS: initializes a Player with a name and corresponding Alien
    public Player(String name, Alien alien) {
        this.name = name;
        this.losses = 0;
        this.wins = 0;
        this.alien = alien;
    }

    public String getName() {
        return name;
    }

    public Alien getAlien() {
        return alien;
    }

    public int getLosses() {
        return losses;
    }

    public int getWins() {
        return wins;
    }

    //MODIFIES: this
    //EFFECTS: increments Player's wins by 1
    public void addWin() {
        wins++;
    }

    //MODIFIES: this
    //EFFECTS: increments Player's losses by 1
    public void addLoss() {
        losses++;
    }

    //MODIFIES: this
    //EFFECTS: sets Player's wins to given integer
    public void setWins(int wins) {
        this.wins = wins;
    }

    //MODIFIES: this
    //EFFECTS: sets Player's losses to given integer
    public void setLosses(int losses) {
        this.losses = losses;
    }


    @Override
    public JSONObject toJson() {
        JSONObject player = new JSONObject();
        player.put("name", name);
        player.put("wins", wins);
        player.put("losses", losses);
        return player;
    }
}