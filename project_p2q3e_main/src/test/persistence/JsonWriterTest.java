package persistence;

import model.Alien;
import model.Connect4Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// used 210's JsonSerializationDemo as a model for this class
public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterInvalidFile() {
        try {
            Connect4Game game = new Connect4Game("Vedant", "Ben");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterGeneralGame() {
        try {
            Connect4Game game = new Connect4Game("Vedant", "Ben");
            game.getBoard().dropAlien(1, Alien.RED);
            game.getBoard().dropAlien(1, Alien.GREEN);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralGame.json");
            writer.open();
            writer.write(game);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralGame.json");
            game = reader.read();
            assertEquals("Vedant", game.getPlayer1().getName());
            assertEquals("Ben", game.getPlayer2().getName());
            assertEquals(Alien.RED, game.getBoard().getCellContent(1,5));
            assertEquals(Alien.GREEN, game.getBoard().getCellContent(1,4));
            assertEquals(Alien.EMPTY, game.getBoard().getCellContent(1,3));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
