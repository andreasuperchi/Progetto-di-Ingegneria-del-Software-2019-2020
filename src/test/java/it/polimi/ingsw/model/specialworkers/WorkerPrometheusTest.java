package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Map;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;
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

    /**
     * tests the move method of WorkerPrometheus in the scenario where a player
     * tries to go up after having used the special power
     */
    @Test(expected = IllegalArgumentException.class)
    public void upMoveAfterSpecialPower() {
        workerPrometheus.specialPower(map.getGrid()[2][3]);
        workerPrometheus.move(map.getGrid()[2][3]);
        assertFalse(Worker.getCanGoUp());
    }

    /**
     * tests the move method of WorkerPrometheus in the scenario where a player
     * doesn't try to go up after having used the special power
     */
    @Test
    public void moveAfterSpecialPower() {
        workerPrometheus.specialPower(map.getGrid()[2][3]);
        workerPrometheus.move(map.getGrid()[1][2]);
        assertTrue(Worker.getCanGoUp());
    }

    /**
     * tests the method specialPower of WorkerPrometheus in the scenario where a player
     * tries to use correctly the special power
     */
    @Test
    public void specialPower() {
        workerPrometheus.specialPower(map.getGrid()[2][3]);
        assertFalse(Worker.getCanGoUp());
    }


    /**
     * tests the method canUseSpecialPower in the scenario where a player
     * tries to use the special power after a move action
     */
    @Test
    public void cantUseSpecialPower() {
        workerPrometheus.move(map.getGrid()[2][3]);
        assertFalse(workerPrometheus.canUseSpecialPower());
    }

    /**
     * tests the method canUseSpecialPower in the scenario where a player
     * tries to use the special power before doing a move action
     */
    @Test
    public void canUseSpecialPower() {
        workerPrometheus.canUseSpecialPower();
        assertTrue(workerPrometheus.canUseSpecialPower());
    }
}