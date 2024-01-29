package ui;

import model.Connect4Game;
// import persistence.JsonReader;
// import persistence.JsonWriter;

import java.io.IOException;
// import java.util.Scanner;

public class PreGame {
//     private Connect4Game game;
//     private JsonReader jsonReader;

    public static Connect4Game noSave(String p1,String p2,int count) {
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Welcome to Connect UF4!");
        // System.out.println("Player 1 Name:");
//        String player1Name = scanner.nextLine();
        // System.out.println("Player 2 Name:");
//        String player2Name = scanner.nextLine();
//        System.out.println(player1Name + " is red and " + player2Name + " will be green.");
        // System.out.println("First to how many games wins: ");
//        int firstTo = Integer.parseInt(scanner.nextLine());
//        Connect4Game game = new Connect4Game(player1Name, player2Name);
        Connect4Game game = new Connect4Game(p1, p2);
//        game.setMaxGames(firstTo);
        game.setMaxGames(count);
        return game;
    }

    public void gameLoad() throws IOException {
        // game = jsonReader.read();
    }

//    public static Connect4Game gameStart() throws IOException {
//        Scanner scanner = new Scanner(System.in);
//        int loading;
//        System.out.println("Do you want to load previous game (1 for yes, 2 for no):");
//        loading = scanner.nextInt();
//        if (loading == 1) {
//            return jsonReader.read();
//        } else {
//            return noSave();
//        }
//    }
}
