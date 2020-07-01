package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerTritonTest {
    Model model;
    Player player1, player2, player3;
    Worker worker;
    WorkerTriton workerTriton;
    Cell baseWorkerCell, nextWorkerCell;

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
        player1.setWorkers(GodName.TRITON);
        player2.setWorkers(GodName.PAN);
        workerTriton = (WorkerTriton) player1.getWorkers()[0];
        worker = player2.getWorkers()[0];
    }

    @Test
    public void checkMultipleMoves() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        workerTriton.setCurrentWorkerCell(baseWorkerCell);
        workerTriton.specialPower(nextWorkerCell);
        assertFalse(player1.getWorkers()[0].getHasUsedSpecialPower());
    }

    @Test
    public void notNearPerimeter() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][1];
        workerTriton.setCurrentWorkerCell(baseWorkerCell);
        assertFalse(workerTriton.verifyPerimeter(nextWorkerCell));
    }

    @Test
    public void cantsUseSpecialPower() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][1];
        workerTriton.setCurrentWorkerCell(baseWorkerCell);
        workerTriton.move(nextWorkerCell);
        assertFalse(workerTriton.canUseSpecialPower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeCheckMove() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][1];
        workerTriton.setCurrentWorkerCell(baseWorkerCell);
        worker.setCurrentWorkerCell(nextWorkerCell);
        workerTriton.specialPower(nextWorkerCell);
    }
}