package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class DirectionTest {

    /**
     * checks the parsing of the "NORTH" string
     */
    @Test
    public void correctNorthParsing() {
        assertEquals(Direction.parseInput("North"), Direction.valueOf("NORTH"));
    }

    /**
     * checks the parsing of the "SOUTH" string
     */
    @Test
    public void correctSouthParsing() {
        assertEquals(Direction.parseInput("South"), Direction.valueOf("SOUTH"));
    }

    /**
     * checks the parsing of the "EAST" string
     */
    @Test
    public void correctEastParsing() {
        assertEquals(Direction.parseInput("East"), Direction.valueOf("EAST"));
    }

    /**
     * checks the parsing of the "WEST" string
     */
    @Test
    public void correctWestParsing() {
        assertEquals(Direction.parseInput("West"), Direction.valueOf("WEST"));
    }

    /**
     * checks the parsing of the "NORTH_EAST" string
     */
    @Test
    public void correctNorthEastParsing() {
        assertEquals(Direction.parseInput("North_East"), Direction.valueOf("NORTH_EAST"));
    }

    /**
     * checks the parsing of the "NORTH_WEST" string
     */
    @Test
    public void correctNorthWestParsing() {
        assertEquals(Direction.parseInput("North_West"), Direction.valueOf("NORTH_WEST"));
    }

    /**
     * checks the parsing of the "SOUTH_EAST" string
     */
    @Test
    public void correctSouthEastParsing() {
        assertEquals(Direction.parseInput("South_East"), Direction.valueOf("SOUTH_EAST"));
    }

    /**
     * checks the parsing of the "SOUTH_WEST" string
     */
    @Test
    public void correctSouthWestParsing() {
        assertEquals(Direction.parseInput("South_West"), Direction.valueOf("SOUTH_WEST"));
    }

    /**
     * checks that the parsing of the string "TEST" produces an IllegalArgumentException
     * (because it's not a valid direction)
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkInvalidInput() {
        assertEquals(Direction.parseInput("Test"), Direction.valueOf("SOUTH_WEST"));
    }
}