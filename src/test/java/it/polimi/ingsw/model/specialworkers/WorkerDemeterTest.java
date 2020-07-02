package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerDemeterTest {
    Map map;
    WorkerDemeter workerDemeter;

    @Before
    public void setUp() {
        map = new Map();
        workerDemeter = new WorkerDemeter();
        workerDemeter.setCurrentWorkerCell(map.getGrid()[2][2]);
    }

    /**
     * tests the build method of WorkerDemeter
     */
    @Test
    public void buildTest() {
        workerDemeter.build(map.getGrid()[2][3]);
        assertEquals(map.getGrid()[2][3], workerDemeter.oldBuildPosition);
    }


    /**
     * tests the method canUseSpecialPower of WorkerDemeter in the scenario where a player
     * tries to use the special power before doing a build action
     */
    @Test
    public void notHasBuiltSpecialPowerTest() {
        workerDemeter.move(map.getGrid()[2][3]);
        assertFalse(workerDemeter.canUseSpecialPower());
    }

    /**
     * tests the method canUseSpecialPower of WorkerDemeter in the scenario where a player
     * already did a move and  build actions
     */
    @Test
    public void canUseSpecialPowerTest() {
        workerDemeter.move(map.getGrid()[2][3]);
        workerDemeter.build(map.getGrid()[2][4]);
        assertTrue(workerDemeter.canUseSpecialPower());
    }

    /**
     * tests the method specialPower of WorkerDemeter in the scenario where a player
     * tries to use specialPower in oldBuildPosition cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void oldBuildPositionSpecialPowerTest() {
        workerDemeter.build(map.getGrid()[2][3]);
        workerDemeter.specialPower(map.getGrid()[2][3]);
        assertFalse(workerDemeter.getHasUsedSpecialPower());
    }

    /**
     * test the method specialPower of WorkerDemeter in the scenario where a player
     * tries to use correctly the special power
     */
    @Test
    public void specialPowerTest() {
        workerDemeter.move(map.getGrid()[2][3]);
        workerDemeter.build(map.getGrid()[3][3]);
        workerDemeter.specialPower(map.getGrid()[4][3]);
        assertTrue(workerDemeter.getHasUsedSpecialPower());
    }
}