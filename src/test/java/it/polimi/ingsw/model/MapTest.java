package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    Map testMap;
    Worker worker;

    @Before
    public void setUp() {
        testMap = new Map();
        worker = new Worker();
    }

    @Test
    public void NorthTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[1][2];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH);
        assertEquals(nextCell, cell);
    }

    @Test
    public void SouthTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[3][2];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH);
        assertEquals(nextCell, cell);
    }

    @Test
    public void EastTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[2][3];
        cell = testMap.getNextWorkerCell(cell, Direction.EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void WestTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[2][1];
        cell = testMap.getNextWorkerCell(cell, Direction.WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void NorthEastTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[1][3];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void NorthWestTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[1][1];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void SouthEastTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[3][3];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void SouthWestTest() {
        Cell cell = testMap.grid[2][2];
        Cell nextCell = testMap.grid[3][1];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertEquals(nextCell, cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullNorthTest() {
        Cell cell = testMap.grid[0][2];
        testMap.getNextWorkerCell(cell, Direction.NORTH);
    }


    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullSouthTest() {
        Cell cell = testMap.grid[4][2];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullEastTest() {
        Cell cell = testMap.grid[2][4];
        cell = testMap.getNextWorkerCell(cell, Direction.EAST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullWestTest() {
        Cell cell = testMap.grid[2][0];
        cell = testMap.getNextWorkerCell(cell, Direction.WEST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullNorthEastTest() {
        Cell cell = testMap.grid[0][4];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullNorthWestTest() {
        Cell cell = testMap.grid[0][0];
        cell = testMap.getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullSouthEastTest() {
        Cell cell = testMap.grid[4][4];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullSouthWestTest() {
        Cell cell = testMap.grid[4][0];
        cell = testMap.getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertNull(cell);
    }

//    @Test
//    void testInitialMap() {
//        worker.symbol = "@A";
//        worker.setCurrentWorkerCell(testMap.grid[4][2]);
//        System.out.println(testMap.showInitialMap());
//    }
//
//    @Test
//    void testToString() {
//        worker.symbol = "@A";
//        worker.setCurrentWorkerCell(testMap.grid[4][2]);
//        testMap.grid[2][2].setLevel(3);
//        testMap.grid[0][1].setLevel(3);
//        testMap.grid[1][1].setLevel(2);
//        testMap.grid[2][1].setLevel(1);
//        testMap.grid[0][4].setLevel(3);
//        testMap.grid[2][3].setLevel(4);
//        testMap.grid[4][1].setLevel(2);
//        System.out.println(testMap.toString());
//    }
}