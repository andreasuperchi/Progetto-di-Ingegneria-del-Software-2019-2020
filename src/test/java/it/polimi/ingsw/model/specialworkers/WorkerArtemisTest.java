package it.polimi.ingsw.model.specialworkers;


import it.polimi.ingsw.model.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class WorkerArtemisTest {
    WorkerArtemis workerArtemis;
    Map map;

    @Before
    public void setUp() {
        map = new Map();
        workerArtemis = new WorkerArtemis();
        workerArtemis.setCurrentWorkerCell(map.getGrid()[2][2]);
    }

    @Test
    public void moveTest() {
        workerArtemis.move(map.getGrid()[2][3]);
        assertEquals(map.getGrid()[2][2], workerArtemis.oldPosition);
    }


    @Test
    public void notHasMovedCanUseSpecialPowerTest() {
        assertFalse(workerArtemis.canUseSpecialPower());
    }

    @Test
    public void hasBuiltCanUseSpecialPower() {
        workerArtemis.move(map.getGrid()[2][3]);
        workerArtemis.build(map.getGrid()[2][4]);
        assertFalse(workerArtemis.canUseSpecialPower());
    }

    @Test
    public void canUseSpecialPower() {
        workerArtemis.move(map.getGrid()[2][3]);
        assertTrue(workerArtemis.canUseSpecialPower());
    }


    @Test(expected = IllegalArgumentException.class)
    public void oldPositionSpecialPowerTest() {
        workerArtemis.move(map.getGrid()[2][3]);
        workerArtemis.specialPower(map.getGrid()[2][2]);
        assertFalse(workerArtemis.getHasUsedSpecialPower());
        assertTrue(workerArtemis.canUseSpecialPower());
    }

    @Test
    public void specialPowerTest() {
        workerArtemis.move(map.getGrid()[2][3]);
        workerArtemis.specialPower(map.getGrid()[2][4]);
        assertTrue(workerArtemis.getHasUsedSpecialPower());
    }
}