package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerAthenaTest {

    Model model;
    Player player1, player2;
    WorkerAthena workerAthena;
    Worker workerOpponent;
    Cell baseWorkerCell, nextWorkerCell, opponentBaseWorkerCell, opponentNextWorkerCell;


    /**
     * sets the game with 2 players and their workers, creates instance of model
     * then set the current player
     */
    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        model = new Model(players, 2);
        player1.setWorkers(GodName.ATHENA);
        player2.setWorkers(GodName.PAN);
        workerAthena = (WorkerAthena) player1.getWorkers()[0];
        workerOpponent = player2.getWorkers()[0];
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        opponentBaseWorkerCell = Model.getMap().getGrid()[2][0];
        opponentNextWorkerCell = Model.getMap().getGrid()[2][1];
    }

    /**
     * check if nextWorkerCell is occupied, workerAthena can't move on nextWorkerCell
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkMoveFalse() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(0);
        nextWorkerCell.setIsOccupied(true);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        assertEquals(baseWorkerCell,workerAthena.getCurrentWorkerCell());
    }

    /**
     * check if workerAthena move to a cell with higher level than currentWorkerCell of workerAthena,
     * CanGoUp is false
     */
    @Test
    public void checkCanGoUpToFalse() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(1);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        assertFalse(Worker.getCanGoUp());
    }

    /**
     * checks if workerAthena move to a cell with no higher level than currentWorkerCell of workerAthena,
     * CanGoUp is true
     */
    @Test
    public void checkCanGoUpToTrue() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(0);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        assertTrue(Worker.getCanGoUp());
    }

    /**
     * checks that an opponent worker can't go up if workerAthena previously has go up
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkOpponentGoUpFalse() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(1);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        //cerco di far salire un avversario dopo che Athena e salita di 1 (non può!)
        opponentBaseWorkerCell.setLevel(0);
        opponentNextWorkerCell.setLevel(1);
        workerOpponent.setCurrentWorkerCell(opponentBaseWorkerCell);
        workerOpponent.move(opponentNextWorkerCell);
        assertEquals(0, workerOpponent.getCurrentWorkerCell().getLevel());

    }

    /**
     * checks that an opponent worker can go up if workerAthena previously hasn't  go up
     */
    @Test
    public void checkOpponentGoUpTrue() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(0);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        //cerco di far salire un avversario senza che Athena è salita di 1 (può!)
        opponentBaseWorkerCell.setLevel(0);
        opponentNextWorkerCell.setLevel(1);
        workerOpponent.setCurrentWorkerCell(opponentBaseWorkerCell);
        workerOpponent.move(opponentNextWorkerCell);
        assertEquals(1, workerOpponent.getCurrentWorkerCell().getLevel());
    }


}