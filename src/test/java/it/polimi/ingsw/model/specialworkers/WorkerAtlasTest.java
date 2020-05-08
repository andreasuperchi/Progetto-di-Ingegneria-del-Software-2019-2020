package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorkerAtlasTest {
    Model model;
    Player player;
    WorkerAtlas workerAtlas;
    Cell baseWorkerCell, nextWorkerCell;

    @Before
    public void setUp() {
        model = new Model();
        player = new Player("Atlas", 30, "#");
        player.setWorkers(GodName.ATLAS);
        Model.setCurrentPlayer(player);
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[2][3];
        workerAtlas = (WorkerAtlas) player.getWorkers()[0];
        workerAtlas.setCurrentWorkerCell(baseWorkerCell);
    }

    @Test
    public void testSpecialPower() {
        workerAtlas.specialPower(nextWorkerCell);
        assertEquals(4, nextWorkerCell.getLevel());
    }

}