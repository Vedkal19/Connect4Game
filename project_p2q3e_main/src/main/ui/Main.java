package ui;

import model.Connect4Game;
import model.Player;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

//Represents the main window where the game is played
public class Main {

    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    public static void main(String[] args) throws InterruptedException, IOException {
        final String JSON_STORE = "./data/game.json";
        JsonWriter jsonWriter = new JsonWriter(JSON_STORE);
        JsonReader jsonReader = new JsonReader(JSON_STORE);
        Scanner scanner = new Scanner(System.in);
        Connect4Game game;
        int loading;
        System.out.println("Do you want to load previous game (1 for yes, 2 for no):");
        loading = scanner.nextInt();
        if (loading == 1) {
            game = jsonReader.read();
        } else {
            game = PreGame.noSave("","",3);
        }

        main:
        while (game.getPlayer1().getWins() < game.getMaxGame()
                && game.getPlayer2().getWins() < game.getMaxGame()) {
            while (!game.gameOverStatus()) {
                System.out.println(game.getCurrent().getName() + "'s turn");
                int key;
                Player prev = game.getCurrent();
                System.out.println("To save current game and quit type 8: ");
                System.out.println("To reset the board type 7: ");
                System.out.println("What column (type 0-6) would you like to drop in: ");
                key = scanner.nextInt();
                if (key == 7) {
                    game.reset();
                    System.out.println("Board reset!");
                } else if (key == 8) {
                    try {
                        jsonWriter.open();
                        jsonWriter.write(game);
                        jsonWriter.close();
                        System.out.println("Saved to " + JSON_STORE);
                        break main;
                    } catch (FileNotFoundException e) {
                        System.out.println("Unable to write to file: " + JSON_STORE);
                    }
                } else {
                    do {
                        if (key == game.getUfo().getColumn()) {
                            System.out.println(game.play(game.getUfo().getColumn()));
                        } else {
                            TimeUnit.SECONDS.sleep(1);
                            game.moveUFO();
                            System.out.println("UFO is above column:" + game.getUfo().getColumn());
                        }
                    } while (prev == game.getCurrent() && !game.gameOverStatus());
                }
                if (game.getBoard().checkTie()) {
                    System.out.println("Game is Tied!");
                } else if (game.getBoard().checkWin(game.getPlayer1().getAlien())
                        || game.getBoard().checkWin(game.getPlayer2().getAlien())) {
                    System.out.println("Game won by " + game.getCurrent().getName());
                }
            }
            System.out.println("Current score:");
            System.out.println(game.getPlayer1().getName() + ": " + game.getPlayer1().getWins() + " ----- "
                    + game.getPlayer2().getName() + ": " + game.getPlayer2().getWins());
            game.reset();
        }
        if (game.getPlayer1().getWins() == game.getMaxGame() || game.getPlayer1().getWins() == game.getMaxGame()) {
            if (game.getPlayer1().getWins() > game.getPlayer2().getWins()) {
                System.out.println("Winner of the match is " + game.getPlayer1().getName() + "!!!");
            } else {
                System.out.println("Winner of the match is " + game.getPlayer2().getName() + "!!!");
            }
        }

    }
}
