package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Map;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerHestiaTest {
    WorkerHestia workerHestia;
    Map map;

    @Before
    public void setUp() {
        map = new Map();
        workerHestia = new WorkerHestia();
        workerHestia.setCurrentWorkerCell(map.getGrid()[2][2]);
    }


    @Test
    public void specialPowerTest() {
        workerHestia.move(map.getGrid()[2][3]);
        workerHestia.build(map.getGrid()[2][2]);
        workerHestia.specialPower(map.getGrid()[2][2]);
        assertTrue(workerHestia.getHasUsedSpecialPower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void perimetricSpecialPowerTest() {
        workerHestia.move(map.getGrid()[3][3]);
        workerHestia.build(map.getGrid()[2][3]);
        workerHestia.specialPower(map.getGrid()[4][3]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void notHasMovedSpecialPowerTest() {
        workerHestia.build(map.getGrid()[2][3]);
        workerHestia.specialPower(map.getGrid()[4][3]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

    @Test(expected = IllegalArgumentException.class)
    public void notHasBuiltSpecialPowerTest() {
        workerHestia.move(map.getGrid()[3][3]);
        workerHestia.specialPower(map.getGrid()[4][3]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

}