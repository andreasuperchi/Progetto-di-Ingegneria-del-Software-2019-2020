package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

public class CellTest {
    Player player1, player2, player3;
    Model model;
    Cell cell;

    /**
     * initializes a game with 3 players and instantiates the model.
     * also stores an utility cell
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
        cell = Model.getMap().getGrid()[1][2];
    }

    /**
     * prints a cell with level set to 2
     */
    @Test
    public void testToStringEmpty() {
        cell.setLevel(2);
        System.out.println(cell.toString());
    }

    /**
     * prints a cell with level set to 2 and occupied by a worker
     */
    @Test
    public void testToString() {
        cell.setLevel(2);
        player1.setWorkers(GodName.APOLLO);
        player1.getWorkers()[0].setCurrentWorkerCell(cell);
        System.out.println(cell.toString());
    }

    /**
     * verifies that setting a cell level to 4 increments the number
     * of completed towers and that this cell is going to be occupied
     * (it won't be possible for a worker to move or build on it)
     */
    @Test
    public void completeTower() {
        cell.setLevel(4);
        assertTrue(cell.getIsOccupied());
        assertEquals(1, Model.getMap().getCompletedTowers());
    }

    /**
     * prints a cell with level set to 4 (has a dome on it)
     */
    @Test
    public void toStringCompletedTower() {
        cell.setLevel(4);
        System.out.println(cell.toString());
    }
}