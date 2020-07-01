package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerMinotaurTest {
    Model model;
    Player player1, player2, player3;
    WorkerMinotaur workerMinotaur;
    Worker otherWorker;
    Cell baseWorkerCell, nextWorkerCell;

    /**
     * initializes a game with 3 players, assigns to 2 of them Minotaur and Pan,
     * instantiates the model object and sets the player1 as the current player.
     * Also stores one workerMinotaur and one workerPan
     */
    @Before
    public void setUp() {
        player1 = new Player("Andre", 5, "@");
        player2 = new Player("Fra", 2, "#");
        player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        player1.setWorkers(GodName.MINOTAUR);
        player2.setWorkers(GodName.PAN);
        workerMinotaur = (WorkerMinotaur) player1.getWorkers()[0];
        otherWorker = player2.getWorkers()[0];
        Model.setCurrentPlayer(player1);
    }

    /**
     * checks that the getCellInThatDirection method behaves correctly
     */
    @Test
    public void checkGetCellInThatDirection() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        assertEquals(Model.getMap().getGrid()[1][3], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that the "force back" works. this means that the worker that was occupying
     * the nextWorkerCell in now going to be occupying the cell coming from the getCellInThatDirection method.
     * also checks that the workerMinotaur has moved to the nextWorkerCell
     */
    @Test
    public void checkForceBack() {
        Player opponent = new Player("Opponent", 33, "#");
        opponent.setWorkers(GodName.PAN);
        Worker workerOpponent = opponent.getWorkers()[0];
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerOpponent.setCurrentWorkerCell(nextWorkerCell);
        workerMinotaur.move(nextWorkerCell);
        assertEquals(nextWorkerCell, workerMinotaur.getCurrentWorkerCell());
        assertEquals(Model.getMap().getGrid()[1][3], workerOpponent.getCurrentWorkerCell());
    }

    /**
     * checks that an IllegalArgumentException is thrown if
     * the worker that occupies the nextWorkerCell is another
     * workerMinotaur
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkIfBothWorkersAreMine() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        player1.getWorkers()[1].setCurrentWorkerCell(nextWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    /**
     * checks that the workerMinotaur doesn't break the conditions
     * of the standard move action. in this case we have tested that he
     * can't go from a level 0 cell to a level 3 cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkStandardCantMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(3);
        workerMinotaur.move(nextWorkerCell);
    }

    /**
     * checks that the workerMinotaur moves correctly in the "standard way"
     */
    @Test
    public void checkStandardCanMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        baseWorkerCell.setLevel(3);
        nextWorkerCell.setLevel(2);
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    /**
     * checks that the workerMinotaur can't move to a cell
     * with a dome built on it
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkMovementWithADome() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        baseWorkerCell.setLevel(3);
        nextWorkerCell.setLevel(4);
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    /**
     * checks that is not possible to set the current cell of the workerMinotaur on
     * a cell that has a dome built on it
     */
    @Test(expected = IllegalArgumentException.class)
    public void setFail() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        baseWorkerCell.setLevel(4);
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
    }

    /**
     * checks that is illegal for the workerMinotaur to move from a cell
     * to the same cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void sameCell() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = baseWorkerCell;
        workerMinotaur.setCurrentWorkerCell(baseWorkerCell);
        workerMinotaur.move(nextWorkerCell);
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the northEast direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the northEast compared to the nextWorkerCell position)
     */
    @Test
    public void northEast() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][3];
        assertEquals(Model.getMap().getGrid()[0][4], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the southEast direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the southEast compared to the nextWorkerCell position)
     */
    @Test
    public void southEast() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[3][3];
        assertEquals(Model.getMap().getGrid()[4][4], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the south direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the south compared to the nextWorkerCell position)
     */
    @Test
    public void south() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[3][2];
        assertEquals(Model.getMap().getGrid()[4][2], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the southWest direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the southWest compared to the nextWorkerCell position)
     */
    @Test
    public void southWest() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[3][1];
        assertEquals(Model.getMap().getGrid()[4][0], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the west direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the west compared to the nextWorkerCell position)
     */
    @Test
    public void west() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[2][1];
        assertEquals(Model.getMap().getGrid()[2][0], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the northWest direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the northWest compared to the nextWorkerCell position)
     */
    @Test
    public void northWest() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][1];
        assertEquals(Model.getMap().getGrid()[0][0], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }

    /**
     * checks that getCellInThatDirection works correctly moving on a line that goes from
     * the baseWorkerCell to the nextWorkerCell (in this case the nextWorkerCell is on the north direction
     * compared to the position of the baseWorkerCell, so the cell obtained from the getCellInThatDirection
     * method is going to be on the north compared to the nextWorkerCell position)
     */
    @Test
    public void north() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        assertEquals(Model.getMap().getGrid()[0][2], workerMinotaur.getCellInThatDirection(baseWorkerCell, nextWorkerCell));
    }
}
