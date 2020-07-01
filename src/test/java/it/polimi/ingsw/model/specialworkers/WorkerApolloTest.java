package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerApolloTest {
    Model model;
    Player player1, player2, player3;
    WorkerApollo workerApollo;
    Worker workerOpponent;
    Cell baseWorkerCell, nextWorkerCell;

    /**
     * initializes a game with 3 players, assigns to 2 of them Apollo and Triton,
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
        player1.setWorkers(GodName.APOLLO);
        player2.setWorkers(GodName.TRITON);
        workerApollo = (WorkerApollo) player1.getWorkers()[0];
        workerOpponent = player2.getWorkers()[0];
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
    }

    /**
     * tests Apollo's move method with another worker (that is not a WorkerApollo)
     * occupying the nextWorkerCell. also verifies that the two workers are now
     * switched
     */
    @Test
    public void checkMoveWithAnotherWorker() {
        workerApollo.setCurrentWorkerCell(baseWorkerCell);
        workerOpponent.setCurrentWorkerCell(nextWorkerCell);
        workerApollo.move(nextWorkerCell);
        assertEquals(nextWorkerCell, workerApollo.getCurrentWorkerCell());
        assertEquals(baseWorkerCell, workerOpponent.getCurrentWorkerCell());
        assertTrue(workerApollo.getHasMoved());
    }

    /**
     * tests Apollo's move method without any other worker occupying the
     * nextWorkerCell
     */
    @Test
    public void checkMoveWithoutAnotherWorker() {
        workerApollo.setCurrentWorkerCell(baseWorkerCell);
        workerApollo.move(nextWorkerCell);
        assertEquals(nextWorkerCell, workerApollo.getCurrentWorkerCell());
        assertTrue(workerApollo.getHasMoved());
    }

    /**
     * tests the behaviour of Apollo's methods if the worker
     * that occupied the nextWorkerCell is another WorkerApollo
     */
    @Test
    public void checkIfBothWorkersAreMine() {
        Model.getAvailableGods().add(GodName.APOLLO);
        workerApollo = (WorkerApollo) player1.getWorkers()[0];
        workerApollo.setCurrentWorkerCell(baseWorkerCell);
        player1.getWorkers()[1].setCurrentWorkerCell(nextWorkerCell);
        assertFalse(workerApollo.checkMove(nextWorkerCell));
        assertFalse(workerApollo.getHasMoved());
    }

    /**
     * tests the case in which the worker can't move (in the nextWorkerCell
     * there is a dome)
     */
    @Test(expected = IllegalArgumentException.class)
    public void cantMove() {
        workerApollo.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(4);
        workerApollo.move(nextWorkerCell);
    }
}