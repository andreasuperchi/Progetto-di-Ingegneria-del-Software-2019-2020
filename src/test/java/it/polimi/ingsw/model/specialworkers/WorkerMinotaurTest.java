package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerMinotaurTest {

    Model model;
    Player p1, p2;
    WorkerMinotaur w1;
    Worker w2;
    Cell c1, c2;

    @Before
    public void setUp() {
        p1 = new Player("Test", 22, "@");
        p2 = new Player("Enemy", 34, "#");
        w1 = new WorkerMinotaur();
        w2 = new Worker();
        model = new Model();
    }

    @Test
    public void checkGetCellInThatDirection() {
        c1 = Model.getMap().getGrid()[1][1];
        c2 = Model.getMap().getGrid()[1][2];
        assertEquals(Model.getMap().getGrid()[1][3], w1.getCellInThatDirection(c1, c2));
    }

    @Test
    public void checkForceBack() {
        c1 = Model.getMap().getGrid()[1][1];
        c2 = Model.getMap().getGrid()[1][2];
        w1.setCurrentWorkerCell(c1);
        w2.setCurrentWorkerCell(c2);
        w1.checkMove(c2);
        w1.move(c2);
        assertEquals(c2, w1.getCurrentWorkerCell());
        assertEquals(Model.getMap().getGrid()[1][3], w2.getCurrentWorkerCell());
    }

    @Test
    public void checkIfBothWorkersAreMine() {
//        model.setAvailableGods(godChoice);
//        model.setPlayerGod(godChoice);
//        c1 = Model.getMap().getGrid()[1][1];
//        c2 = Model.getMap().getGrid()[1][2];
//        w1 = (WorkerMinotaur) p1.getWorkers()[0];
//        w1.setCurrentWorkerCell(c1);
//        p1.getWorkers()[1].setCurrentWorkerCell(c2);
//        Model.setCurrentPlayer(p1);
//        assertFalse(w1.checkMove(c2));
    }
}