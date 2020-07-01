package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerAtlasTest {
    Model model;
    Player player1, player2, player3;
    WorkerAtlas workerAtlas;
    Cell baseWorkerCell, nextWorkerCell;

    /**
     * initializes a game with 3 players, assigns to 2 of them Atlas and Pan,
     * instantiates the model object and sets the player1 as the current player.
     * Also stores 2 utility cells
     */
    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        player1.setWorkers(GodName.ATLAS);
        player2.setWorkers(GodName.PAN);
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[2][3];
        workerAtlas = (WorkerAtlas) player1.getWorkers()[0];
        workerAtlas.setCurrentWorkerCell(baseWorkerCell);
    }

    /**
     * checks if the worker uses his special power correctly
     * (he is building a dome)
     */
    @Test
    public void testSpecialPower() {
        workerAtlas.specialPower(nextWorkerCell);
        assertEquals(4, nextWorkerCell.getLevel());
        assertTrue(workerAtlas.getHasUsedSpecialPower());
    }

    /**
     * tests the standard move method
     */
    @Test
    public void normalMove() {
        workerAtlas.move(nextWorkerCell);
        assertEquals(0, nextWorkerCell.getLevel());
        assertTrue(workerAtlas.getHasMoved());
        assertFalse(workerAtlas.getHasUsedSpecialPower());
    }

    /**
     * tests if the worker behave correctly after having moved
     * and having used the special power
     */
    @Test
    public void moveAndSpecialPower() {
        Cell otherCell = Model.getMap().getGrid()[2][4];
        workerAtlas.move(nextWorkerCell);
        workerAtlas.specialPower(otherCell);
        assertEquals(Model.getMap().getGrid()[2][3], workerAtlas.getCurrentWorkerCell());
        assertEquals(4, otherCell.getLevel());
        assertTrue(workerAtlas.getHasUsedSpecialPower());
    }

    /**
     * checks if the worker can't use his special power knowing
     * that there's a dome on the nextWorkerCell
     */
    @Test(expected = IllegalArgumentException.class)
    public void cantBuild() {
        nextWorkerCell.setLevel(4);
        workerAtlas.specialPower(nextWorkerCell);
    }

    /**
     * checks that the worker can use his specialPower after
     * having moved
     */
    @Test
    public void cantUseSpecialPower() {
        workerAtlas.move(nextWorkerCell);
        assertTrue(workerAtlas.canUseSpecialPower());
    }
}