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


    @Test(expected = IllegalArgumentException.class)
    public void checkMoveFalse() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(0);
        nextWorkerCell.setIsOccupied(true);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        assertEquals(baseWorkerCell,workerAthena.getCurrentWorkerCell());
    }

    @Test
    public void checkCanGoUpToFalse() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(1);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        assertFalse(Worker.getCanGoUp());
    }

    @Test
    public void checkCanGoUpToTrue() {
        baseWorkerCell.setLevel(0);
        nextWorkerCell.setLevel(0);
        workerAthena.setCurrentWorkerCell(baseWorkerCell);
        workerAthena.move(nextWorkerCell);
        assertTrue(Worker.getCanGoUp());
    }

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