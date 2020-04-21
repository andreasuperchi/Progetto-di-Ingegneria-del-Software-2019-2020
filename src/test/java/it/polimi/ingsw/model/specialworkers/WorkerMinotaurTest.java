package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class WorkerMinotaurTest {

    Model model;
    Player p1, p2;
    WorkerMinotaur w1;
    Worker w2;
    Cell c1, c2;
    ArrayList<Player> players;
    boolean gameOver = false;

    @BeforeEach
    void setUp() {
        p1 = new Player("Test", 22);
        p2 = new Player("Enemy", 34);
        w1 = new WorkerMinotaur(p1);
        w2 = new Worker(p2);
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


}