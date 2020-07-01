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


    @Test
    public void buildTest() {
        workerDemeter.build(map.getGrid()[2][3]);
        assertEquals(map.getGrid()[2][3], workerDemeter.oldBuildPosition);
    }

    @Test
    public void notHasBuiltSpecialPowerTest() {
        workerDemeter.move(map.getGrid()[2][3]);
        assertFalse(workerDemeter.canUseSpecialPower());
    }

    @Test
    public void caUseSpecialPowerTest() {
        workerDemeter.move(map.getGrid()[2][3]);
        workerDemeter.build(map.getGrid()[2][4]);
        assertTrue(workerDemeter.canUseSpecialPower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void oldBuildPositionSpecialPowerTest() {
        workerDemeter.build(map.getGrid()[2][3]);
        workerDemeter.specialPower(map.getGrid()[2][3]);
        assertFalse(workerDemeter.getHasUsedSpecialPower());
    }

    @Test
    public void specialPowerTest() {
        workerDemeter.move(map.getGrid()[2][3]);
        workerDemeter.build(map.getGrid()[3][3]);
        workerDemeter.specialPower(map.getGrid()[4][3]);
        assertTrue(workerDemeter.getHasUsedSpecialPower());
    }
}