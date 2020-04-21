package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DirectionTest {

    @Test
    public void correctNorthParsing() {

        assertEquals(Direction.parseInput("North"), Direction.valueOf("NORTH"));
    }

    @Test
    public void correctSouthParsing() {

        assertEquals(Direction.parseInput("South"), Direction.valueOf("SOUTH"));
    }

    @Test
    public void correctEastParsing() {

        assertEquals(Direction.parseInput("East"), Direction.valueOf("EAST"));
    }

    @Test
    public void correctWestParsing() {

        assertEquals(Direction.parseInput("West"), Direction.valueOf("WEST"));
    }

    @Test
    public void correctNorthEastParsing() {
        assertEquals(Direction.parseInput("North_East"), Direction.valueOf("NORTH_EAST"));
    }

    @Test
    public void correctNorthWestParsing() {
        assertEquals(Direction.parseInput("North_West"), Direction.valueOf("NORTH_WEST"));
    }

    @Test
    public void correctSouthEastParsing() {
        assertEquals(Direction.parseInput("South_East"), Direction.valueOf("SOUTH_EAST"));
    }

    @Test
    public void correctSouthWestParsing() {
        assertEquals(Direction.parseInput("South_West"), Direction.valueOf("SOUTH_WEST"));
    }

    // TODO: fare caso in cui l'input sia errato
}