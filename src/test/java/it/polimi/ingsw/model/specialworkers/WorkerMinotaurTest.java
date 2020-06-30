package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerMinotaurTest {
    Model model;
    Player player1, player2, player3;
    WorkerMinotaur workerMinotaur;
    Worker otherWorker;
    Cell baseWorkerCell, nextWorkerCell;

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
        player1.setWorkers(GodName.MINOTAUR);
        player2.setWorkers(GodName.ZEUS);
        workerMinotaur = (WorkerMinotaur) player1.getWorkers()[0];
        otherWorker = player2.getWorkers()[0];
        Model.setCurrentPlayer(player1);
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
        player1.getWorkers()[1].setCurrentWorkerCell(nextWorkerCell);
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

    @Test(expected = IllegalArgumentException.class)
    public void setFail() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        baseWorkerCell.setLevel(4);
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sameCell() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = baseWorkerCell;
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    @Test
    public void northEast() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][3];
        assertEquals(Model.getMap().getGrid()[0][4], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void southEast() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[3][3];
        assertEquals(Model.getMap().getGrid()[4][4], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void south() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[3][2];
        assertEquals(Model.getMap().getGrid()[4][2], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void southWest() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[3][1];
        assertEquals(Model.getMap().getGrid()[4][0], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void west() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[2][1];
        assertEquals(Model.getMap().getGrid()[2][0], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void northWest() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][1];
        assertEquals(Model.getMap().getGrid()[0][0], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    @Test
    public void north() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        assertEquals(Model.getMap().getGrid()[0][2], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }
}
