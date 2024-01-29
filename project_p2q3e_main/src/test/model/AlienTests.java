package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlienTests {
    private Alien alien;

    @BeforeEach
    void runBefore() {
        alien = new Alien("red");
    }

    @Test
    void testGetColour() {
        assertEquals("red", alien.getColour());
    }
}
