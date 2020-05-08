package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerMinotaurTest {
    Model model;
    Player player;
    WorkerMinotaur workerMinotaur;
    Cell baseWorkerCell, nextWorkerCell;

    @Before
    public void setUp() {
        model = new Model();
        player = new Player("Test", 22, "@");
        player.setWorkers(GodName.MINOTAUR);
        workerMinotaur = (WorkerMinotaur) player.getWorkers()[0];
        Model.setCurrentPlayer(player);
    }

    @Test
    public void checkGetCellInThatDirection() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        assertEquals(Model.getMap().getGrid()[1][3], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void checkForceBack() {
        Player opponent = new Player("Opponent", 33, "#");
        opponent.setWorkers(GodName.ZEUS);
        Worker workerOpponent = opponent.getWorkers()[0];
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerOpponent.setCurrentWorkerCell(nextWorkerCell);
        workerMinotaur.move(nextWorkerCell);
        assertEquals(nextWorkerCell, workerMinotaur.getCurrentWorkerCell());
        assertEquals(Model.getMap().getGrid()[1][3], workerOpponent.getCurrentWorkerCell());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIfBothWorkersAreMine() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        player.getWorkers()[1].setCurrentWorkerCell(nextWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkStandardCantMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(3);
        workerMinotaur.move(nextWorkerCell);
    }

    @Test
    public void checkStandardCanMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        baseWorkerCell.setLevel(3);
        nextWorkerCell.setLevel(2);
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkMovementWithADome() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        baseWorkerCell.setLevel(3);
        nextWorkerCell.setLevel(4);
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }
}