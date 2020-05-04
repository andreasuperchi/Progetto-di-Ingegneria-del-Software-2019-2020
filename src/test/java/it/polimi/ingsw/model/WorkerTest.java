package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

class WorkerTest {
    Player player;
    Worker worker;
    Model model;

    @BeforeEach
    void setUp() {
        player = new Player("Test", 22);
        model = new Model();
    }

    @Test
    public void checkIfNorthIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfEastIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWestIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestIsValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[0][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[4][4];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfEastCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[0][4];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWestCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[0][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[3][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[4][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestCellIsNotValid() {
        Cell baseCellWorker = Model.getMap().getGrid()[4][1];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = Model.getMap().getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCantGoUp() {
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        Cell nextCellWorker = Model.getMap().getGrid()[1][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        nextCellWorker.setLevel(2);
        assertFalse(worker.checkMove(nextCellWorker));
    }

    @Test
    public void checkIfNextCellIsOccupied() {
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        Cell nextCellWorker = Model.getMap().getGrid()[1][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        nextCellWorker.setIsOccupied(true);
        assertFalse(worker.checkMove(nextCellWorker));
    }

    @Test
    public void checkCorrectMove() {
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        Cell nextCellWorker = Model.getMap().getGrid()[1][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.move(nextCellWorker);
        assertEquals(nextCellWorker, worker.getCurrentWorkerCell());
    }

    @Test
    public void checkIfWorkerOccupiesCell() {
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        Cell nextCellWorker = Model.getMap().getGrid()[1][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.move(nextCellWorker);
        assertTrue(worker.getCurrentWorkerCell().getIsOccupied());
    }

    @Test
    public void checkIfOldCellIsEmpty() {
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        Cell nextCellWorker = Model.getMap().getGrid()[1][0];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.move(nextCellWorker);
        assertFalse(baseCellWorker.getIsOccupied());
    }

    @Test
    public void checkCorrectBuild() {
        Cell baseCellWorker = Model.getMap().getGrid()[2][2];
        Cell nextCellWorker = Model.getMap().getGrid()[1][2];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.build(nextCellWorker);
        assertEquals(1, nextCellWorker.getLevel());
    }

    @Test
    public void checkCorrectWinCondition() {
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        Cell nextCellWorker = Model.getMap().getGrid()[0][1];
        worker = new Worker();
        worker.setCurrentWorkerCell(baseCellWorker);
        baseCellWorker.setLevel(2);
        nextCellWorker.setLevel(3);
        worker.move(nextCellWorker);
        assertTrue(worker.winCondition());
    }

    @Test
    public void checkIfWorkerCantMove() {
        worker = new Worker();
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextCellWorker = Model.getMap().getGrid()[0][0];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[0][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[0][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[1][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[2][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[2][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[2][0];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[1][0];
        nextCellWorker.setLevel(4);
        assertFalse(worker.checkSurroundingCells());
    }

    @Test
    public void checkIfWorkerCanMove() {
        worker = new Worker();
        Cell baseCellWorker = Model.getMap().getGrid()[1][1];
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextCellWorker = Model.getMap().getGrid()[0][0];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[0][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[0][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[1][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[2][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[2][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = Model.getMap().getGrid()[2][0];
        nextCellWorker.setLevel(4);
        assertTrue(worker.checkSurroundingCells());
    }
}