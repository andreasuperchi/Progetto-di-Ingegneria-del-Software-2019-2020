package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerHephaestusTest {

    Model model;
    Player player1, player2;
    WorkerHephaestus workerHephaestus;
    Cell baseWorkerCell, nextWorkerCell, otherCell;

    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        model = new Model(players, 2);
        player1.setWorkers(GodName.HEPHAESTUS);
        player2.setWorkers(GodName.CHARON);
        workerHephaestus = (WorkerHephaestus) player1.getWorkers()[0];
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        otherCell = Model.getMap().getGrid()[0][1];
    }

    @Test
    public void buildAgainTrue() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        workerHephaestus.specialPower(nextWorkerCell);
        assertEquals(2, nextWorkerCell.getLevel());

    }

    @Test(expected = IllegalArgumentException.class)
    public void buildAgainFalse() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        //provo a fagli costruire su una cella diversa da nextWorkerCell (non puo!)
        workerHephaestus.specialPower(otherCell);
        assertEquals(1, nextWorkerCell.getLevel());
        assertEquals(0, otherCell.getLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void buildDomeFalse() {
        nextWorkerCell.setLevel(2);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell); //livello -> 3
        //provo a fagli costruire una cupola (non può!)
        workerHephaestus.specialPower(nextWorkerCell);
        assertEquals(3, nextWorkerCell.getLevel());
    }


}