package it.polimi.ingsw.model.specialworkers;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import static org.junit.jupiter.api.Assertions.*;

class WorkerPanTest {
    WorkerPan workerPan;

    @BeforeEach
    void setUp() {
        workerPan = new WorkerPan();
    }

    @Test
    void winConditionPanTrue() {
        workerPan.setOldLevel(3);
        workerPan.setNewLevel(1);
        assertTrue(workerPan.winCondition());
    }


    @Test
    void winConditionGenericTrue() {
        workerPan.setOldLevel(2);
        workerPan.setNewLevel(3);
        assertTrue(workerPan.winCondition());
    }

    @Test
    void winConditionFalse() {
        workerPan.setOldLevel(3);
        workerPan.setNewLevel(2);
        assertFalse(workerPan.winCondition());
    }
}