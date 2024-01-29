package persistence;

import model.Alien;
import model.Connect4Game;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// used 210's JsonSerializationDemo as a model for this class
public class JsonReaderTest extends JsonTest{
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Connect4Game game = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderGeneralGame() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralGame.json");
        try {
            Connect4Game game = reader.read();
            assertEquals("Vedant", game.getPlayer1().getName());
            assertEquals("Ben", game.getPlayer2().getName());
            assertEquals(Alien.RED, game.getBoard().getCellContent(1,5));
            assertEquals(Alien.GREEN, game.getBoard().getCellContent(1,4));
            assertEquals(Alien.EMPTY, game.getBoard().getCellContent(1,3));
            assertEquals("Ben", game.getCurrent().getName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
