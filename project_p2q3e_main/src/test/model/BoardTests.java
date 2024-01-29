package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTests {
    private Board board;

    @BeforeEach
    void runBefore() {
        board = new Board();
    }

    @Test
    void testMakeBoard() {
        for(int x = 0; x < Board.COLUMNS; x++) {
            for(int y = 0; y < Board.ROWS; y++) {
                assertEquals(Alien.EMPTY, board.getCellContent(x,y));
            }
        }
    }

    @Test
    void testDropAlien() {
        assertTrue(board.dropAlien(0, Alien.RED));
        assertEquals(Alien.RED, board.getCellContent(0,5));
        assertTrue(board.dropAlien(0, Alien.GREEN));
        assertEquals(Alien.GREEN, board.getCellContent(0,4));
        assertFalse(board.dropAlien(-1, Alien.RED));
        assertFalse(board.dropAlien(7, Alien.RED));
    }

    @Test
    void testColumnFull() {
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        assertFalse(board.dropAlien(0, Alien.RED));
    }

    @Test
    void testForTie() {
        assertFalse(board.checkTie());
        for(int x = 0; x < Board.ROWS; x++) {
            for(int y = 0; y < Board.COLUMNS; y++) {
                board.dropAlien(y, Alien.GREEN);
            }
        }
        assertTrue(board.checkTie());
    }

    @Test
    void testHorWin() {
        assertFalse(board.checkHor(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(1, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(2, Alien.RED);
        board.dropAlien(2, Alien.GREEN);
        assertFalse(board.checkHor(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(3, Alien.RED);
        assertTrue(board.checkHor(Alien.RED));
        assertTrue(board.checkWin(Alien.RED));
    }

    @Test
    void testVertTrue() {
        assertFalse(board.checkVert(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(0, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        assertFalse(board.checkVert(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(0, Alien.RED);
        assertTrue(board.checkVert(Alien.RED));
        assertTrue(board.checkWin(Alien.RED));
    }

    @Test
    void testVertFalse() {
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        assertFalse(board.checkVert(Alien.RED));
        assertFalse(board.checkVert(Alien.GREEN));
        board.dropAlien(1, Alien.RED);
        board.dropAlien(1, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(1, Alien.RED);
        assertFalse(board.checkVert(Alien.RED));
        assertFalse(board.checkVert(Alien.GREEN));
        board.dropAlien(2, Alien.RED);
        board.dropAlien(2, Alien.GREEN);
        board.dropAlien(2, Alien.RED);
        board.dropAlien(2, Alien.RED);
        assertFalse(board.checkVert(Alien.RED));
        assertFalse(board.checkVert(Alien.GREEN));
    }

    @Test
    void testDiag1TrBlTrue() {
        assertFalse(board.checkDiag1(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(0, Alien.RED);
        board.dropAlien(6, Alien.GREEN);
        board.dropAlien(6, Alien.RED);
        board.dropAlien(6, Alien.GREEN);
        board.dropAlien(6, Alien.RED);
        board.dropAlien(5, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(5, Alien.GREEN);
        board.dropAlien(5, Alien.RED);
        assertFalse(board.checkDiag1(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(4, Alien.GREEN);
        board.dropAlien(4, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        assertFalse(board.checkDiag1(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(3, Alien.RED);
        assertTrue(board.checkDiag1(Alien.RED));
        assertTrue(board.checkWin(Alien.RED));
    }

    @Test
    void testDiag1TrBlFalse() {
        board.dropAlien(6, Alien.RED);
        board.dropAlien(6, Alien.GREEN);
        board.dropAlien(6, Alien.RED);
        board.dropAlien(6, Alien.GREEN);
        board.dropAlien(5, Alien.RED);
        board.dropAlien(5, Alien.GREEN);
        board.dropAlien(5, Alien.RED);
        board.dropAlien(4, Alien.GREEN);
        board.dropAlien(4, Alien.RED);
        board.dropAlien(3, Alien.GREEN);
        assertFalse(board.checkDiag1(Alien.GREEN));
        assertFalse(board.checkDiag1(Alien.RED));

    }

    @Test
    void testDiag2WinTlBrTrue() {
        assertFalse(board.checkDiag2(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(6, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(6, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(1, Alien.RED);
        assertFalse(board.checkDiag2(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(2, Alien.GREEN);
        board.dropAlien(2, Alien.RED);
        board.dropAlien(6, Alien.GREEN);
        assertFalse(board.checkDiag2(Alien.RED));
        assertFalse(board.checkWin(Alien.RED));
        board.dropAlien(3, Alien.RED);
        assertTrue(board.checkDiag2(Alien.RED));
        assertTrue(board.checkWin(Alien.RED));
    }

    @Test
    void testDiag2WinTlBrFalse() {
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(0, Alien.RED);
        board.dropAlien(0, Alien.GREEN);
        board.dropAlien(1, Alien.RED);
        board.dropAlien(1, Alien.GREEN);
        board.dropAlien(1, Alien.RED);
        board.dropAlien(2, Alien.GREEN);
        board.dropAlien(2, Alien.RED);
        board.dropAlien(3, Alien.GREEN);
        assertFalse(board.checkDiag2(Alien.GREEN));
        assertFalse(board.checkDiag2(Alien.RED));
    }

    @Test
    void testGetBoard() {
        board.dropAlien(0, Alien.RED);
        board.dropAlien(2, Alien.RED);
        board.dropAlien(0, Alien.RED);

        ArrayList<ArrayList<Alien>> result = board.getBoard();

        assertEquals(result.get(0).get(5), Alien.RED);
        assertEquals(result.get(0).get(4), Alien.RED);
        assertEquals(result.get(2).get(5), Alien.RED);
        assertEquals(result.get(0).get(3), Alien.EMPTY);
        assertEquals(result.get(1).get(5), Alien.EMPTY);
        assertEquals(result.get(2).get(4), Alien.EMPTY);
    }
}
