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

    /**
     * tests the move method of WorkerArtemis
     */
    @Test
    public void moveTest() {
        workerArtemis.move(map.getGrid()[2][3]);
        assertEquals(map.getGrid()[2][2], workerArtemis.oldPosition);
    }

    /**
     * tests the method canUseSpecialPower of WorkerDemeter in the scenario where a player
     * tries to use the special power before doing a move action
     */
    @Test
    public void notHasMovedCanUseSpecialPowerTest() {
        assertFalse(workerArtemis.canUseSpecialPower());
    }


    /**
     * tests the method canUseSpecialPower of WorkerArtemis in the scenario where a player
     * tries to use the special power after a build action
     */
    @Test
    public void hasBuiltCanUseSpecialPower() {
        workerArtemis.move(map.getGrid()[2][3]);
        workerArtemis.build(map.getGrid()[2][4]);
        assertFalse(workerArtemis.canUseSpecialPower());
    }


    /**
     * tests the method canUseSpecialPower of WorkerArtemis in the scenario where a player
     * already did a move action
     */
    @Test
    public void canUseSpecialPower() {
        workerArtemis.move(map.getGrid()[2][3]);
        assertTrue(workerArtemis.canUseSpecialPower());
    }


    /**
     * tests the method specialPower of WorkerArtemis in the scenario where a player
     * tries to use specialPower in oldPosition cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void oldPositionSpecialPowerTest() {
        workerArtemis.move(map.getGrid()[2][3]);
        workerArtemis.specialPower(map.getGrid()[2][2]);
        assertFalse(workerArtemis.getHasUsedSpecialPower());
        assertTrue(workerArtemis.canUseSpecialPower());
    }


    /**
     * tests the method specialPower of WorkerArtemis in the scenario where a player
     * tries to use correctly the special power
     */
    @Test
    public void specialPowerTest() {
        workerArtemis.move(map.getGrid()[2][3]);
        workerArtemis.specialPower(map.getGrid()[2][4]);
        assertTrue(workerArtemis.getHasUsedSpecialPower());
    }
}