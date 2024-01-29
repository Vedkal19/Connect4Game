package ui;


import java.io.IOException;

import model.Connect4Game;
import persistence.JsonReader;


//Creates a new game
public class GuiGame {
    Connect4Game game;

    //Modifies: this
    //Effects: starts completely new game
    GuiGame(String p1, String p2, int count) {
        game = PreGame.noSave(p1, p2, count);
    }

    //Modifies: this
    //Effects: loads from the saved file
    GuiGame() {
        final String JSON_STORE = "./data/game.json";
        JsonReader jsonReader = new JsonReader(JSON_STORE);

        try {
            game = jsonReader.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Effects: starts the game with board and displays gui
    public void start() {
        GuiBoard board = new GuiBoard(game);
        board.createAndShowGUI();
        // game.close();
    }


}