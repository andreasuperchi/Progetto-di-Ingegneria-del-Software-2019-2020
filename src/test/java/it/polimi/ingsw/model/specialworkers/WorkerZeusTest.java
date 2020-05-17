package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerZeusTest {
    Model model;
    Player player1, player2;
    WorkerZeus workerZeus;
    Cell baseWorkerCell;

    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        model = new Model(players, 2);
        player1.setWorkers(GodName.ZEUS);
        player2.setWorkers(GodName.CHARON);
        workerZeus = (WorkerZeus) player1.getWorkers()[0];
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[1][1];


    }

    @Test
    public void buildUnderZeus() {
        baseWorkerCell.setLevel(0);
        workerZeus.setCurrentWorkerCell(baseWorkerCell);
        workerZeus.build(baseWorkerCell);
        assertEquals(1, workerZeus.getCurrentWorkerCell().getLevel());
        assertFalse(model.isGameOver());
    }


    @Test
    public void buildUnderZeusToLevel3() {
        baseWorkerCell.setLevel(2);
        workerZeus.setCurrentWorkerCell(baseWorkerCell);
        workerZeus.build(baseWorkerCell);
        assertEquals(3, workerZeus.getCurrentWorkerCell().getLevel());
        assertFalse(model.isGameOver());
    }
}