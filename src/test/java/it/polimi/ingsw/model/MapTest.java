package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest {
    Model model;
    Worker worker;
    Player player;

    @Before
    public void setUp() {
        model = new Model();
        worker = new Worker();
    }

    @Test
    public void northTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[1][2];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH);
        assertEquals(nextCell, cell);
    }

    @Test
    public void southTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[3][2];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH);
        assertEquals(nextCell, cell);
    }

    @Test
    public void eastTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[2][3];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void westTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[2][1];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void northEastTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[1][3];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void northWestTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[1][1];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void southEastTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[3][3];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertEquals(nextCell, cell);
    }

    @Test
    public void southWestTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[3][1];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertEquals(nextCell, cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullNorthTest() {
        Cell cell = Model.getMap().getGrid()[0][2];
        Model.getMap().getNextWorkerCell(cell, Direction.NORTH);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullSouthTest() {
        Cell cell = Model.getMap().getGrid()[4][2];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullEastTest() {
        Cell cell = Model.getMap().getGrid()[2][4];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.EAST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullWestTest() {
        Cell cell = Model.getMap().getGrid()[2][0];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.WEST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullNorthEastTest() {
        Cell cell = Model.getMap().getGrid()[0][4];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullNorthWestTest() {
        Cell cell = Model.getMap().getGrid()[0][0];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullSouthEastTest() {
        Cell cell = Model.getMap().getGrid()[4][4];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertNull(cell);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void NullSouthWestTest() {
        Cell cell = Model.getMap().getGrid()[4][0];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertNull(cell);
    }

//    @Test
//    public void testInitialMap() {
//        System.out.println(Model.getMap().showInitialMap());
//        worker.symbol = "@A";
//        worker.setCurrentWorkerCell(Model.getMap().getGrid()[4][2]);
//        System.out.println("\n\n\n\n" + Model.getMap().showInitialMap());
//    }

//    @Test
//    public void testToString() {
//        player = new Player("Test", 33, "#");
//        player.setWorkers(GodName.APOLLO);
//        player.getWorkers()[0].setCurrentWorkerCell(Model.getMap().getGrid()[1][2]);
//        player.getWorkers()[1].setCurrentWorkerCell(Model.getMap().getGrid()[1][3]);
//        Model.getMap().getGrid()[2][2].setLevel(3);
//        Model.getMap().getGrid()[0][1].setLevel(3);
//        Model.getMap().getGrid()[1][1].setLevel(2);
//        Model.getMap().getGrid()[2][1].setLevel(1);
//        Model.getMap().getGrid()[0][4].setLevel(3);
//        Model.getMap().getGrid()[2][3].setLevel(4);
//        Model.getMap().getGrid()[4][1].setLevel(2);
//        System.out.println(Model.getMap().toString());
//    }
}