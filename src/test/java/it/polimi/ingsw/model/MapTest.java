package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class MapTest {
    Model model;
    Player player1, player2, player3;
    Worker worker;

    /**
     * initializes a game with 3 players and instantiates the model.
     * also stores an utility worker
     */
    @Before
    public void setUp() {
        player1 = new Player("Andre", 5, "@");
        player2 = new Player("Fra", 2, "#");
        player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        worker = new Worker();
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the north direction in this case)
     */
    @Test
    public void northTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[1][2];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the south direction in this case)
     */
    @Test
    public void southTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[3][2];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the east direction in this case)
     */
    @Test
    public void eastTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[2][3];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.EAST);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the west direction in this case)
     */
    @Test
    public void westTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[2][1];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.WEST);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the northEast direction in this case)
     */
    @Test
    public void northEastTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[1][3];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the northWest direction in this case)
     */
    @Test
    public void northWestTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[1][1];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the southEast direction in this case)
     */
    @Test
    public void southEastTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[3][3];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the method getNextWorkerCell works correctly
     * (returns a cell in the southWest direction in this case)
     */
    @Test
    public void southWestTest() {
        Cell cell = Model.getMap().getGrid()[2][2];
        Cell nextCell = Model.getMap().getGrid()[3][1];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertEquals(nextCell, cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the north direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullNorthTest() {
        Cell cell = Model.getMap().getGrid()[0][2];
        Model.getMap().getNextWorkerCell(cell, Direction.NORTH);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the south direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullSouthTest() {
        Cell cell = Model.getMap().getGrid()[4][2];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH);
        assertNull(cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the east direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullEastTest() {
        Cell cell = Model.getMap().getGrid()[2][4];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.EAST);
        assertNull(cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the west direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullWestTest() {
        Cell cell = Model.getMap().getGrid()[2][0];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.WEST);
        assertNull(cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the northEast direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullNorthEastTest() {
        Cell cell = Model.getMap().getGrid()[0][4];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_EAST);
        assertNull(cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the northWest direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullNorthWestTest() {
        Cell cell = Model.getMap().getGrid()[0][0];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.NORTH_WEST);
        assertNull(cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the southEast direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullSouthEastTest() {
        Cell cell = Model.getMap().getGrid()[4][4];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_EAST);
        assertNull(cell);
    }

    /**
     * checks that the getNextWorkerCell throws an IllegalArgumentException
     * if we try to retrieve a cell out of the map in the southWest direction
     */
    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void nullSouthWestTest() {
        Cell cell = Model.getMap().getGrid()[4][0];
        cell = Model.getMap().getNextWorkerCell(cell, Direction.SOUTH_WEST);
        assertNull(cell);
    }

    /**
     * prints out the initial map with a worker in it
     */
    @Test
    public void testInitialMap() {
        System.out.println(Model.getMap().showInitialMap());
        worker.symbol = "@A";
        worker.setCurrentWorkerCell(Model.getMap().getGrid()[4][2]);
        System.out.println("\n\n\n\n" + Model.getMap().showInitialMap());
    }

    /**
     * prints out the map with various levels and with 2 workers
     */
    @Test
    public void testToString() {
        player1.setWorkers(GodName.APOLLO);
        player1.getWorkers()[0].setCurrentWorkerCell(Model.getMap().getGrid()[1][2]);
        player1.getWorkers()[1].setCurrentWorkerCell(Model.getMap().getGrid()[1][3]);
        Model.getMap().getGrid()[2][2].setLevel(3);
        Model.getMap().getGrid()[0][1].setLevel(3);
        Model.getMap().getGrid()[1][1].setLevel(2);
        Model.getMap().getGrid()[2][1].setLevel(1);
        Model.getMap().getGrid()[0][4].setLevel(3);
        Model.getMap().getGrid()[2][3].setLevel(4);
        Model.getMap().getGrid()[4][1].setLevel(2);
        System.out.println(Model.getMap().toString());
    }
}