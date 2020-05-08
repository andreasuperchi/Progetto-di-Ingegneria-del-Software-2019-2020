package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerTritonTest {
    Model model;
    Player player;
    Worker worker;
    Cell baseWorkerCell, nextWorkerCell;

    @Before
    public void setUp() {
        model = new Model();
        player = new Player("Test", 22, "#");
        player.setWorkers(GodName.TRITON);
        worker = player.getWorkers()[0];
    }

    @Test
    public void checkMultipleMoves() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertFalse(player.getWorkers()[0].getHasMoved());
    }
}