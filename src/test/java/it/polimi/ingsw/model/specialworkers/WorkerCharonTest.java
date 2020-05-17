package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerCharonTest {

    Model model;
    Player player1, player2;
    WorkerCharon workerCharonA, workerCharonB;
    Worker workerOpponentA, workerOpponentB;
    Cell charonCellA, charonCellB, opponentWorkerCellA, opponentWorkerCellB, distantCell;

    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        model = new Model(players, 2);
        player1.setWorkers(GodName.CHARON);
        player2.setWorkers(GodName.ZEUS);
        workerCharonA = (WorkerCharon) player1.getWorkers()[0];
        workerCharonB = (WorkerCharon) player1.getWorkers()[1];
        workerOpponentA = player2.getWorkers()[0];
        workerOpponentB = player2.getWorkers()[1];
        Model.setCurrentPlayer(player1);
        charonCellA = Model.getMap().getGrid()[1][1];
        charonCellB = Model.getMap().getGrid()[0][1];
        opponentWorkerCellA = Model.getMap().getGrid()[1][2];
        opponentWorkerCellB = Model.getMap().getGrid()[1][0];
        distantCell = Model.getMap().getGrid()[3][3];
    }


    @Test(expected = IllegalArgumentException.class)
    public void specialPowerWithNoWorkersAround() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(distantCell);
        workerCharonA.specialPower(distantCell);
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialPowerOnOtherCharon() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerCharonB.setCurrentWorkerCell(charonCellB);
        workerCharonA.specialPower(charonCellB);
    }

    @Test
    public void specialPowerOnOpponentWorkerOk() {
        Cell symmetricalCell = Model.getMap().getGrid()[1][0];   //dove finirà worker avversario

        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(opponentWorkerCellA);
        workerCharonA.specialPower(opponentWorkerCellA);
        assertEquals(symmetricalCell, workerOpponentA.getCurrentWorkerCell());
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialPowerOnOpponentWorkerSymmetricalCellOccupied() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(opponentWorkerCellA);
        workerOpponentB.setCurrentWorkerCell(opponentWorkerCellB);
        workerCharonA.specialPower(opponentWorkerCellA);
    }

    @Test(expected = IllegalArgumentException.class)
    public void specialPowerSymmetricalCellOutMap() {
        charonCellA = Model.getMap().getGrid()[0][0];
        opponentWorkerCellA = Model.getMap().getGrid()[0][1];

        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(opponentWorkerCellA);
        workerCharonA.specialPower(opponentWorkerCellA);
    }

}