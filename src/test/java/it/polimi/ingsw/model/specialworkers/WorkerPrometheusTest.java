package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Map;
import it.polimi.ingsw.model.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerPrometheusTest {
    Map map;
    WorkerPrometheus workerPrometheus;


    @Before
    public void setUp() {
        workerPrometheus = new WorkerPrometheus();
        map = new Map();
        workerPrometheus.setCurrentWorkerCell(map.getGrid()[2][2]);
    }

    @Test(expected = IllegalArgumentException.class)
    public void upMoveAfterSpecialPower() {
        workerPrometheus.specialPower(map.getGrid()[2][3]);
        workerPrometheus.move(map.getGrid()[2][3]);
        assertFalse(workerPrometheus.getCanGoUp());
    }

    @Test
    public void moveAfterSpecialPower() {
        workerPrometheus.specialPower(map.getGrid()[2][3]);
        workerPrometheus.move(map.getGrid()[1][2]);
        assertTrue(workerPrometheus.getCanGoUp());
    }

    @Test
    public void specialPower() {
        workerPrometheus.specialPower(map.getGrid()[2][3]);
        assertFalse(workerPrometheus.getCanGoUp());
    }
}