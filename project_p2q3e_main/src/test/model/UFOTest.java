package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UFOTest {
    private UFO ufo1;
    private UFO ufo2;
    private UFO ufo3;
    private UFO ufo4;


    @BeforeEach
    void runBefore() {
        ufo1 = new UFO(0, 5);
        ufo2 = new UFO(-1, 5);
        ufo3 = new UFO(6, 5);
        ufo4 = new UFO(7, 5);
    }

    @Test
    void testMakeUFO() {
        assertEquals(0, ufo1.getColumn());
        assertEquals(5, ufo1.getHeight());
        assertEquals(1, ufo1.getDirection());
    }

    @Test
    void testMove() {
        assertEquals(0, ufo1.getColumn());
        ufo1.move();
        assertEquals(1, ufo1.getColumn());
    }

    @Test
    void testLowBoundary() {
        assertEquals(-1, ufo2.getColumn());
        ufo2.boundary();
        assertEquals(0, ufo2.getColumn());
    }

    @Test
    void testHighBoundary() {
        assertEquals(7, ufo4.getColumn());
        ufo4.boundary();
        assertEquals(Board.COLUMNS - 1, ufo4.getColumn());
    }

    @Test
    void testBoundaryDirectionChange() {
        assertEquals(1, ufo3.getDirection());
        assertEquals(6, ufo3.getColumn());
        ufo3.move();
        assertEquals(-1, ufo3.getDirection());
        assertEquals(Board.COLUMNS - 1, ufo3.getColumn());
    }
}
