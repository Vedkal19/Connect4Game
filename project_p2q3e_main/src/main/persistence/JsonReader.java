package persistence;

import model.Alien;
import model.Connect4Game;
import model.Event;
import model.EventLog;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;


// import static model.Board.COLUMNS;
// import static model.Board.ROWS;

// used 210's JsonSerializationDemo as a model for this class
// Represents a reader that reads Connect4Game from JSON data stored in file
public class JsonReader {
    private String source;
    private EventLog log;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        log = EventLog.getInstance();
        this.source = source;
    }

    // EFFECTS: reads Connect4Game from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Connect4Game read() throws IOException {
        String jsonData = "";
        // try {
        jsonData = readFile(source);
        // } catch (IOException e) {
            
        //     String desc = "Error in reading input File " + e.getMessage();
        //     log.logEvent(new Event(desc));
        //     e.printStackTrace();
        // }
        JSONObject jsonObject = new JSONObject(jsonData);
        log.logEvent(new Event("File has been read"));
        return parseGame(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Connnect4Game from JSON object and returns it
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private Connect4Game parseGame(JSONObject jsonObject) {
        JSONObject player1 = jsonObject.getJSONObject("player1");
        JSONObject player2 = jsonObject.getJSONObject("player2");
        Connect4Game ngame = new Connect4Game(player1.getString("name"), player2.getString("name"));
        ngame.setMaxGames(jsonObject.getInt("max"));
        ngame.getPlayer1().setWins(player1.getInt("wins"));
        ngame.getPlayer1().setLosses(player1.getInt("losses"));
        ngame.getPlayer2().setWins(player2.getInt("wins"));
        ngame.getPlayer2().setLosses(player2.getInt("losses"));
        JSONArray boardList = jsonObject.getJSONArray("board");

        for (Object rowList : boardList) {
            int col = 0;
            for (Object color : (JSONArray) rowList) {
                String newColor = (String) color;
                if (newColor.equals("red")) {
                    ngame.getBoard().dropAlien(col, Alien.RED);
                } else if (newColor.equals("green")) {
                    ngame.getBoard().dropAlien(col, Alien.GREEN);
                }
                col++;
            }
        }
        if (jsonObject.getString("current").equals(ngame.getPlayer2().getName())) {
            ngame.switchPlayer();
        }
        return ngame;
    }
}
