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


    /**
     * sets the game with 2 players and their workers (A and B respectively), creates instance of model
     * then set the current player
     */
    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        model = new Model(players, 2);
        player1.setWorkers(GodName.CHARON);
        player2.setWorkers(GodName.PAN);
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

    /**
     * checks workerCharonA can use special power, knowing has't move and hasn't build
     */
    @Test
    public void canUseSpecialPowerTrue() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        assertTrue(workerCharonA.canUseSpecialPower());
    }

    /**
     * checks workerCharonA can't use special power, knowing has move and hasn't build
     */
    @Test
    public void canUseSpecialPowerFalse() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerCharonA.setHasMoved(true);
        assertFalse(workerCharonA.canUseSpecialPower());
    }

    /**
     * checks workerCharonA can't use special power, knowing there aren't opponent worker
     * in the adjacent cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void specialPowerWithNoWorkersAround() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(distantCell);
        workerCharonA.specialPower(distantCell);
    }

    /**
     * checks workerCharonA can't use special power if the cell of the
     * special power (distantCell) isn't adjacent to workerCharonA
     */
    @Test(expected = IllegalArgumentException.class)
    public void specialPowerOnDistantCell() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerCharonA.specialPower(distantCell);
    }


    /**
     * checks workerCharonA can't use special power if on the cell of the
     * special power (charonCellB) there is the other WorkerCharon (workerCharonB)
     */
    @Test(expected = IllegalArgumentException.class)
    public void specialPowerOnOtherCharon() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerCharonB.setCurrentWorkerCell(charonCellB);
        workerCharonA.specialPower(charonCellB);
    }


    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is NORTH relatively to workerCharonA, thus opponent worker will be move
     * SOUTH relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerNorth() {
        Cell symmetricalCell = Model.getMap().getGrid()[2][1];   //dove finirà worker avversario (SOUTH)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[0][1]);   //North
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is NORTH-EAST relatively to workerCharonA, thus opponent worker will be move
     * SOUTH_WEST relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerNorthEast() {
        Cell symmetricalCell = Model.getMap().getGrid()[2][0];   //dove finirà worker avversario (SOUTH-WEAST)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[0][2]);   //North-east
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is EAST relatively to workerCharonA, thus opponent worker will be move
     * WEST relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerEast() {
        Cell symmetricalCell = Model.getMap().getGrid()[1][0];   //dove finirà worker avversario (WEST)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[1][2]);   //EAST
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is SOUTH-EAST relatively to workerCharonA, thus opponent worker will be move
     * NORTH-WEST relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerSouthEast() {
        Cell symmetricalCell = Model.getMap().getGrid()[0][0];   //dove finirà worker avversario (SOUTH)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[2][2]);   //South-east
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is SOUTH relatively to workerCharonA, thus opponent worker will be move
     * NORTH relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerSouth() {
        Cell symmetricalCell = Model.getMap().getGrid()[0][1];   //dove finirà worker avversario (SOUTH)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[2][1]);   //South
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is SOUTH-WEST relatively to workerCharonA, thus opponent worker will be move
     * NORTH-EAST relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerSouthWest() {
        Cell symmetricalCell = Model.getMap().getGrid()[0][2];   //dove finirà worker avversario (SOUTH)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[2][0]);   //southWest
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is WEST relatively to workerCharonA, thus opponent worker will be move
     * EAST relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerWest() {
        Cell symmetricalCell = Model.getMap().getGrid()[1][2];   //dove finirà worker avversario (SOUTH)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[1][0]);   //west
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }

    /**
     * checks workerCharonA (placed on cell [1][1]) can use special power on an opponent worker
     * that is NORTH-WEST relatively to workerCharonA, thus opponent worker will be move
     * SOUTH-EAST relatively to workerCharonA
     */
    @Test
    public void specialPowerOnOpponentWorkerNorthWest() {
        Cell symmetricalCell = Model.getMap().getGrid()[2][2];   //dove finirà worker avversario (SOUTH)

        workerCharonA.setCurrentWorkerCell(charonCellA);                        //[1][1]
        workerOpponentA.setCurrentWorkerCell(Model.getMap().getGrid()[0][0]);   //NorthWest
        workerCharonA.specialPower(workerOpponentA.getCurrentWorkerCell());
        assertEquals(workerOpponentA.getCurrentWorkerCell(), symmetricalCell);
    }



    /**
     * checks workerCharonA can't use special power if the symmetrical cell of opponentWorkerCellA
     * is occupied by an other worker (workerOpponentB)
     */
    @Test(expected = IllegalArgumentException.class)
    public void specialPowerOnOpponentWorkerSymmetricalCellOccupied() {
        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(opponentWorkerCellA);
        workerOpponentB.setCurrentWorkerCell(opponentWorkerCellB);
        workerCharonA.specialPower(opponentWorkerCellA);
    }

    /**
     * checks workerCharonA[0][0] can't use special power if the symmetrical cell
     * of opponentWorkerCellA (cell [0][1]) is out of the map (cell [0][-1] not exist)
     */
    @Test(expected = IllegalArgumentException.class)
    public void specialPowerSymmetricalCellOutMap() {
        charonCellA = Model.getMap().getGrid()[0][0];
        opponentWorkerCellA = Model.getMap().getGrid()[0][1];

        workerCharonA.setCurrentWorkerCell(charonCellA);
        workerOpponentA.setCurrentWorkerCell(opponentWorkerCellA);
        workerCharonA.specialPower(opponentWorkerCellA);
    }

}