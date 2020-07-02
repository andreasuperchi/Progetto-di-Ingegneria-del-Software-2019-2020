package it.polimi.ingsw.model.specialworkers;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerPanTest {
    WorkerPan workerPan;

    @Before
    public void setUp() {
        workerPan = new WorkerPan();
    }

    /**
     * tests WorkerPan's additional win condition
     */
    @Test
    public void winConditionPanTrue() {
        workerPan.setOldLevel(3);
        workerPan.setNewLevel(1);
        assertTrue(workerPan.winCondition());
    }

    /**
     * tests the generic win conditions
     */
    @Test
    public void winConditionGenericTrue() {
        workerPan.setOldLevel(2);
        workerPan.setNewLevel(3);
        assertTrue(workerPan.winCondition());
    }

    /**
     * tests the method winCondition in the scenario where
     * a player doesn't win
     */
    @Test
    public void winConditionFalse() {
        workerPan.setOldLevel(3);
        workerPan.setNewLevel(2);
        assertFalse(workerPan.winCondition());
    }
}