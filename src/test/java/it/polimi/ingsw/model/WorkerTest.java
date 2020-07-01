package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerTest {
    Player player1, player2, player3;
    Worker worker;
    Model model;
    Cell baseWorkerCell, nextWorkerCell;

    /**
     * creates a new game with 3 players, instantiates the model and
     * assigns Pan to the player1. the player1 is also set as the current player
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
        player1.setWorkers(GodName.PAN);
        worker = player1.getWorkers()[0];
    }

    /**
     * checks if the worker can move to another cell(in this case in north direction)
     */
    @Test
    public void checkIfNorthIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in south direction)
     */
    @Test
    public void checkIfSouthIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in east direction)
     */
    @Test
    public void checkIfEastIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in west direction)
     */
    @Test
    public void checkIfWestIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in northEast direction)
     */
    @Test
    public void checkIfNorthEastIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in northWest direction)
     */
    @Test
    public void checkIfNorthWestIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in southEast direction)
     */
    @Test
    public void checkIfSouthEastIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks if the worker can move to another cell(in this case in southWest direction)
     */
    @Test
    public void checkIfSouthWestIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks that the worker can go up if the difference between the level of
     * the current cell and the next cell is equals to 1
     */
    @Test
    public void checkIfWorkerCanGoUp() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        baseWorkerCell.setLevel(1);
        nextWorkerCell.setLevel(2);
        worker.setCurrentWorkerCell(baseWorkerCell);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks that the worker can't go up if the difference between the level of
     * the current cell and the next cell is equals to 2
     */
    @Test
    public void checkIfWorkerCantGoUp() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(2);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks that the worker can go down from a level 3 cell to a level 0 cell
     */
    @Test
    public void checkIfWorkerCanJumpDown() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        baseWorkerCell.setLevel(3);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks that the worker can't go up if Athena has gone up in this turn
     */
    @Test
    public void checkIfWorkerCantGoUpAthena() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        Worker.setCanGoUp(false);
        nextWorkerCell.setLevel(1);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks that the worker can't move to an occupied cell
     */
    @Test
    public void checkIfNextCellIsOccupied() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setIsOccupied(true);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    /**
     * checks that the occupied flag is set correctly when moving
     */
    @Test
    public void checkIfWorkerOccupiesCell() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertTrue(worker.getCurrentWorkerCell().getIsOccupied());
    }

    /**
     * checks that, after having moved, the starting cell results not occupied
     */
    @Test
    public void checkIfOldCellIsEmpty() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertFalse(baseWorkerCell.getIsOccupied());
    }

    /**
     * checks if the worker moves correctly
     */
    @Test
    public void checkCorrectMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertEquals(nextWorkerCell, worker.getCurrentWorkerCell());
    }

    /**
     * checks that the worker can't move from a level 0 cell to a level 3 cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(3);
        worker.move(nextWorkerCell);
    }

    /**
     * checks that the worker correctly builds, adding 1 to the level of the nextWorkerCell
     * (that previously was at level 0)
     */
    @Test
    public void checkCorrectBuild() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.build(nextWorkerCell);
        assertEquals(1, nextWorkerCell.getLevel());
    }

    /**
     * checks that the worker can't build anything on a dome
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectBuild() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(4);
        worker.build(nextWorkerCell);
    }

    /**
     * checks that the method winCondition works correctly
     */
    @Test
    public void checkCorrectWinCondition() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        worker.setCurrentWorkerCell(baseWorkerCell);
        baseWorkerCell.setLevel(2);
        nextWorkerCell.setLevel(3);
        worker.move(nextWorkerCell);
        assertTrue(worker.winCondition());
    }

    /**
     * checks that the winCondition method returns false if the starting level is different from 2
     * and the final level is different from 3
     */
    @Test
    public void checkWinConditionFail() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        worker.setCurrentWorkerCell(baseWorkerCell);
        baseWorkerCell.setLevel(1);
        nextWorkerCell.setLevel(2);
        worker.move(nextWorkerCell);
        assertFalse(worker.winCondition());
    }

    /**
     * checks that the method checkSurroundingCells works correctly and
     * returns false if all the surrounding cells are at level 4
     */
    @Test
    public void checkIfWorkerCantMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getGrid()[0][0];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[0][2];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[2][1];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[2][0];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        nextWorkerCell.setLevel(4);
        assertFalse(worker.checkSurroundingCells());
    }

    /**
     * checks that the method checkSurroundingCells works correctly and
     * returns true if not all the surrounding cells are occupied
     */
    @Test
    public void checkIfWorkerCanMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getGrid()[0][0];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[0][2];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[2][1];
        nextWorkerCell.setLevel(4);
        nextWorkerCell = Model.getMap().getGrid()[2][0];
        nextWorkerCell.setLevel(4);
        assertTrue(worker.checkSurroundingCells());
    }

    /**
     * checks that the worker can't be set on a cell with a dome on it
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkCellWithADome() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        baseWorkerCell.setLevel(4);
        worker.setCurrentWorkerCell(baseWorkerCell);
    }

    /**
     * checks that the action of building a tower is done correctly
     */
    @Test
    public void buildATower() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[0][1];
        Cell otherCell = Model.getMap().getGrid()[0][0];
        otherCell.setLevel(3);
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        worker.build(otherCell);
        assertTrue(otherCell.getIsOccupied());
        assertEquals(1, Model.getMap().getCompletedTowers());
    }
}