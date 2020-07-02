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

    /**
     * tests the method specialPower of WorkerHestia in the scenario where a player
     * tries to use correctly the special power
     */
    @Test
    public void specialPowerTest() {
        workerHestia.move(map.getGrid()[2][3]);
        workerHestia.build(map.getGrid()[2][2]);
        workerHestia.specialPower(map.getGrid()[2][2]);
        assertTrue(workerHestia.getHasUsedSpecialPower());
    }

    /**
     * tests the method specialPower of WorkerHestia in the scenario where a player
     * tries to use specialPower in a perimetrical row cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void perimetricRowSpecialPowerTest() {
        workerHestia.move(map.getGrid()[3][3]);
        workerHestia.build(map.getGrid()[2][3]);
        workerHestia.specialPower(map.getGrid()[4][3]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

    /**
     * tests the method specialPower of WorkerHestia in the scenario where a player
     * tries to use specialPower in a perimetrical column cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void perimeterColumnSpecialPowerTest() {
        workerHestia.move(map.getGrid()[3][3]);
        workerHestia.build(map.getGrid()[2][3]);
        workerHestia.specialPower(map.getGrid()[3][4]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

    /**
     * tests the method specialPower of WorkerDemeter in the scenario where a player
     * tries to use the special power before doing a move action
     */
    @Test(expected = IllegalArgumentException.class)
    public void notHasMovedSpecialPowerTest() {
        workerHestia.build(map.getGrid()[2][3]);
        workerHestia.specialPower(map.getGrid()[4][3]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

    /**
     * tests the method specialPower of WorkerHestia in the scenario where a player
     * tries to use the special power before doing a build action
     */
    @Test(expected = IllegalArgumentException.class)
    public void notHasBuiltSpecialPowerTest() {
        workerHestia.move(map.getGrid()[3][3]);
        workerHestia.specialPower(map.getGrid()[4][3]);
        assertFalse(workerHestia.getHasUsedSpecialPower());
    }

    /**
     * tests the method canUseSpecial power of WorkerHestia in the scenario where a player
     * tries to use the special power before doing a move action
     */
    @Test
    public void notHasMovedCanUseSpecialPower() {
        assertFalse(workerHestia.canUseSpecialPower());
    }

    /**
     * tests the method canUseSpecial power of WorkerHestia in the scenario where a player
     * tries to use the special power before doing a build action
     */
    @Test
    public void notHasBuiltCanUseSpecialPower() {
        workerHestia.move(map.getGrid()[3][3]);
        assertFalse(workerHestia.canUseSpecialPower());
    }

    /**
     * tests the method canUseSpecialPower of WorkerHestia in the scenario where a player
     * already did a move and build actions
     */
    @Test
    public void canUseSpecialPower() {
        workerHestia.move(map.getGrid()[3][3]);
        workerHestia.build(map.getGrid()[3][4]);
        assertTrue(workerHestia.canUseSpecialPower());
    }

}