package persistence;

import model.Player;

import static org.junit.jupiter.api.Assertions.assertEquals;

// used 210's JsonSerializationDemo as a model for this class
public class JsonTest {
    protected void checkThingy(String name, int wins, int losses, Player player) {
        assertEquals(name, player.getName());
        assertEquals(wins, player.getWins());
        assertEquals(losses, player.getLosses());
    }
}
