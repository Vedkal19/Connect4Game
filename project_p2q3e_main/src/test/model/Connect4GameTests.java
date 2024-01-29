package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class Connect4GameTests {
    private Connect4Game game;

    @BeforeEach
    void runBefore() {
        game = new Connect4Game("Ved", "Yash");
    }

    @Test
    void testMakeGame() {
        assertEquals(game.getPlayer1(), game.getCurrent());
        assertFalse(game.gameOverStatus());
    }

    @Test
    void testSwitch() {
        assertEquals(game.getPlayer1(), game.getCurrent());
        game.switchPlayer();
        assertEquals(game.getPlayer2(), game.getCurrent());
    }

    @Test
    void testPlayValid() {
        assertTrue(game.play(0));
        assertEquals(Alien.RED, game.getBoard().getCellContent(0, 5));
        assertEquals(Alien.GREEN, game.getCurrent().getAlien());
    }

    @Test
    void testPlayInvalid() {
        assertTrue(game.play(0));
        assertTrue(game.play(0));
        assertTrue(game.play(0));
        assertTrue(game.play(0));
        assertTrue(game.play(0));
        assertTrue(game.play(0));
        assertFalse(game.play(0));
        assertEquals(Alien.GREEN, game.getBoard().getCellContent(0, 0));
        assertEquals(Alien.RED, game.getCurrent().getAlien());
    }

    @Test
    void testPlayWin() {
        //p1 plays in c0
        game.play(0);
        assertEquals(game.getPlayer2(), game.getCurrent());
        assertFalse(game.gameOverStatus());

        //p2 plays in c0
        game.play(0);
        assertEquals(game.getPlayer1(), game.getCurrent());
        assertFalse(game.gameOverStatus());

        //p1 plays in c1
        game.play(1);
        assertEquals(game.getPlayer2(), game.getCurrent());
        assertFalse(game.gameOverStatus());

        //p2 plays in c1
        game.play(1);
        assertEquals(game.getPlayer1(), game.getCurrent());
        assertFalse(game.gameOverStatus());

        //p1 plays in c2
        game.play(2);
        assertEquals(game.getPlayer2(), game.getCurrent());
        assertFalse(game.gameOverStatus());

        //p2 plays in c2
        game.play(2);
        assertEquals(game.getPlayer1(), game.getCurrent());
        assertFalse(game.gameOverStatus());

        //p1 plays in c3 for win
        game.play(3);
        assertTrue(game.gameOverStatus());
    }

    @Test
    void testGameOverTie() {
        assertFalse(game.gameOverStatus());
        for(int x = 0; x < Board.ROWS; x++) {
            game.play(0);
            game.play(1);
            game.play(2);
            game.play(3);
            game.play(4);
            game.play(5);
            assertFalse(game.getBoard().checkTie());
            game.play(6);
        }
        assertFalse(game.getBoard().checkWin(Alien.RED));
        assertTrue(game.getBoard().checkTie());
        assertTrue(game.gameOverStatus());
    }

    @Test
    void testReset() {
        game.play(0);
        game.reset();
        assertFalse(game.gameOverStatus());
        assertEquals(Alien.EMPTY, game.getBoard().getCellContent(0,5));
    }

    @Test
    void testMoveUFO() {
        assertEquals(0, game.getUfo().getColumn());
        game.moveUFO();
        assertEquals(1, game.getUfo().getColumn());
    }

    @Test
    void testGetUFO() {
        assertEquals(0, game.getUfo().getColumn());
        assertEquals(5, game.getUfo().getHeight());
    }

    @Test
    void testGetBoard() {
        assertEquals(Alien.EMPTY, game.getBoard().getCellContent(0,0));
        assertEquals(Alien.EMPTY, game.getBoard().getCellContent(6,5));
    }

    @Test
    void testGetMaxGame() {
        game.setMaxGames(5);
        assertEquals(5, game.getMaxGame());
    }
}
