package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerAtlasTest {
    Model model;
    Player player1, player2, player3;
    WorkerAtlas workerAtlas;
    Cell baseWorkerCell, nextWorkerCell;

    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        player1.setWorkers(GodName.ATLAS);
        player2.setWorkers(GodName.ZEUS);
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[2][3];
        workerAtlas = (WorkerAtlas) player1.getWorkers()[0];
        workerAtlas.setCurrentWorkerCell(baseWorkerCell);
    }

    @Test
    public void testSpecialPower() {
        workerAtlas.specialPower(nextWorkerCell);
        assertEquals(4, nextWorkerCell.getLevel());
    }

}