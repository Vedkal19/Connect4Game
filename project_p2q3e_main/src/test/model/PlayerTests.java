package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTests {
    private Player player;

    @BeforeEach
    void runBefore() {
        player = new Player("Ved", new Alien("red"));
    }

    @Test
    void testMakePlayer() {
        assertEquals("Ved", player.getName());
        assertEquals(0, player.getLosses());
        assertEquals(0, player.getWins());
    }

    @Test
    void testSetWinLoss() {
        player.setWins(5);
        player.setLosses(3);
        assertEquals(3, player.getLosses());
        assertEquals(5, player.getWins());
    }

    @Test
    void testAddWin() {
        player.addWin();
        assertEquals(1, player.getWins());
        player.addWin();
        assertEquals(2, player.getWins());
    }

    @Test
    void testAddLoss() {
        player.addLoss();
        assertEquals(1, player.getLosses());
    }
}
