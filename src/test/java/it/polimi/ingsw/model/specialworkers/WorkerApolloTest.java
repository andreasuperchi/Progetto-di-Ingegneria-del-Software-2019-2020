package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertFalse;

class WorkerApolloTest {
    Model model;
    Player p1, p2;
    WorkerApollo w1;
    Worker w2;
    Cell c1, c2;

    @BeforeEach
    void setup() {
        model = new Model();
        p1 = new Player("Test", 23);
        p2 = new Player("Enemy", 25);
        w1 = new WorkerApollo();
        w2 = new Worker();
    }

    @Test
    public void checkMoveWithAnotherWorker() {
        c1 = Model.getMap().getGrid()[1][1];
        c2 = Model.getMap().getGrid()[1][2];
        w1.setCurrentWorkerCell(c1);
        w2.setCurrentWorkerCell(c2);
        w1.move(c2);
        assertEquals(c2, w1.getCurrentWorkerCell());
        assertEquals(c1, w2.getCurrentWorkerCell());
    }

    @Test
    public void checkMoveWithoutAnotherWorker() {
        c1 = Model.getMap().getGrid()[1][1];
        c2 = Model.getMap().getGrid()[1][2];
        w1.setCurrentWorkerCell(c1);
        w1.move(c2);
        assertEquals(c2, w1.getCurrentWorkerCell());
    }

    @Test
    public void checkIfBothWorkersAreMine() {
        Model.getAvailableGods().add(GodName.APOLLO);
        c1 = Model.getMap().getGrid()[1][1];
        c2 = Model.getMap().getGrid()[1][2];
        w1 = (WorkerApollo) p1.getWorkers()[0];
        w1.setCurrentWorkerCell(c1);
        p1.getWorkers()[1].setCurrentWorkerCell(c2);
        assertFalse(w1.checkMove(c2));
    }
}