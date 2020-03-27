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
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[1][2];
        cell=testMap.getNextCellWorker(cell,Direction.NORTH);
        assertEquals(nextCell,cell);
    }



    @Test
    void SouthTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[3][2];
        cell = testMap.getNextCellWorker(cell,Direction.SOUTH);
        assertEquals(nextCell,cell);
    }

    @Test
    void EastTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[2][3];
        cell = testMap.getNextCellWorker(cell,Direction.EAST);
        assertEquals(nextCell,cell);
    }

    @Test
    void WestTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[2][1];
        cell = testMap.getNextCellWorker(cell,Direction.WEST);
        assertEquals(nextCell,cell);
    }

    @Test
    void NorthEastTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[1][3];
        cell = testMap.getNextCellWorker(cell,Direction.NORTH_EAST);
        assertEquals(nextCell,cell);
    }

    @Test
    void NorthWestTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[1][1];
        cell = testMap.getNextCellWorker(cell,Direction.NORTH_WEST);
        assertEquals(nextCell,cell);
    }

    @Test
    void SouthEastTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[3][3];
        cell = testMap.getNextCellWorker(cell,Direction.SOUTH_EAST);
        assertEquals(nextCell,cell);
    }

    @Test
    void SouthWestTest(){
        Cell cell = testMap.map[2][2];
        Cell nextCell = testMap.map[3][1];
        cell = testMap.getNextCellWorker(cell,Direction.SOUTH_WEST);
        assertEquals(nextCell,cell);
    }

    @Test
    void NullNorthTest(){
        Cell cell = testMap.map[0][2];
        cell = testMap.getNextCellWorker(cell, Direction.NORTH);
        assertNull(cell);
    }


    @Test
    void NullSouthTest(){
        Cell cell = testMap.map[4][2];
        cell = testMap.getNextCellWorker(cell, Direction.SOUTH);
        assertNull(cell);
    }

    @Test
    void NullEastTest(){
        Cell cell = testMap.map[2][4];
        cell = testMap.getNextCellWorker(cell, Direction.EAST);
        assertNull(cell);
    }

    @Test
    void NullWestTest(){
        Cell cell = testMap.map[2][0];
        cell = testMap.getNextCellWorker(cell, Direction.WEST);
        assertNull(cell);
    }

    @Test
    void NullNorthEastTest(){
        Cell cell = testMap.map[0][4];
        cell = testMap.getNextCellWorker(cell, Direction.NORTH_EAST);
        assertNull(cell);
    }

    @Test
    void NullNorthWestTest(){
        Cell cell = testMap.map[0][0];
        cell = testMap.getNextCellWorker(cell, Direction.NORTH_WEST);
        assertNull(cell);
    }

    @Test
    void NullSouthEastTest(){
        Cell cell = testMap.map[4][4];
        cell = testMap.getNextCellWorker(cell, Direction.SOUTH_EAST);
        assertNull(cell);
    }

    @Test
    void NullSouthWestTest(){
        Cell cell = testMap.map[4][0];
        cell = testMap.getNextCellWorker(cell, Direction.SOUTH_WEST);
        assertNull(cell);
    }



}