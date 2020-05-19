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
        player1.setWorkers(GodName.ZEUS);
        worker = player1.getWorkers()[0];
    }

    @Test
    public void checkIfNorthIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfEastIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWestIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestIsValid() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCanGoUp() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        baseWorkerCell.setLevel(1);
        nextWorkerCell.setLevel(2);
        worker.setCurrentWorkerCell(baseWorkerCell);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCantGoUp() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(2);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCanJumpDown() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        baseWorkerCell.setLevel(3);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCantGoUpAthena() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.setCanGoUp(false);
        nextWorkerCell.setLevel(1);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNextCellIsOccupied() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setIsOccupied(true);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerOccupiesCell() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertTrue(worker.getCurrentWorkerCell().getIsOccupied());
    }

    @Test
    public void checkIfOldCellIsEmpty() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertFalse(baseWorkerCell.getIsOccupied());
    }
    
    @Test
    public void checkCorrectMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.move(nextWorkerCell);
        assertEquals(nextWorkerCell, worker.getCurrentWorkerCell());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectMove() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(3);
        worker.move(nextWorkerCell);
    }

    @Test
    public void checkCorrectBuild() {
        baseWorkerCell = Model.getMap().getGrid()[2][2];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        worker.setCurrentWorkerCell(baseWorkerCell);
        worker.build(nextWorkerCell);
        assertEquals(1, nextWorkerCell.getLevel());
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkIncorrectBuild() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][0];
        worker.setCurrentWorkerCell(baseWorkerCell);
        nextWorkerCell.setLevel(4);
        worker.build(nextWorkerCell);
    }

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

    @Test(expected = IllegalArgumentException.class)
    public void checkCellWithADome() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        baseWorkerCell.setLevel(4);
        worker.setCurrentWorkerCell(baseWorkerCell);
    }
}