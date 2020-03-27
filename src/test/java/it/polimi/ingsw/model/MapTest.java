package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    Map testMap;

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        testMap = new Map();
    }

    @Test
    void NorthTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[1][2];
        cell = testMap.getNextWorkerCell(cell,Direction.NORTH);
        assertEquals(nextCell, cell);
    }

    @Test
    void SouthTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[3][2];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH);
        assertEquals(nextCell, cell);
    }

    @Test
    void EastTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[2][3];
        cell = testMap.getNextWorkerCell(cell, Direction.EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    void WestTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[2][1];
        cell = testMap.getNextWorkerCell(cell, Direction.WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    void NorthEastTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[1][3];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertEquals(nextCell,cell);
    }

    @Test
    void NorthWestTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[1][1];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    void SouthEastTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[3][3];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    void SouthWestTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[3][1];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    void NullNorthTest() {
        Cell cell = testMap.grid[0][2];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH);
        assertEquals(null,cell);
    }


    @Test
    void NullSouthTest() {
        Cell cell = testMap.grid[4][2];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH);
        assertEquals(null, cell);
    }

    @Test
    void NullEastTest() {
        Cell cell = testMap.grid[2][4];
        cell = testMap.getNextWorkerCell(cell, Direction.EAST);
        assertEquals(null, cell);
    }

    @Test
    void NullWestTest() {
        Cell cell = testMap.grid[2][0];
        cell = testMap.getNextWorkerCell(cell, Direction.WEST);
        assertEquals(null, cell);
    }

    @Test
    void NullNorthEastTest() {
        Cell cell = testMap.grid[0][4];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertEquals(null, cell);
    }

    @Test
    void NullNorthWestTest() {
        Cell cell = testMap.grid[0][0];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertEquals(null, cell);
    }

    @Test
    void NullSouthEastTest() {
        Cell cell = testMap.grid[4][4];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertEquals(null, cell);
    }

    @Test
    void NullSouthWestTest() {
        Cell cell = testMap.grid[4][0];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertEquals(null, cell);
    }

}