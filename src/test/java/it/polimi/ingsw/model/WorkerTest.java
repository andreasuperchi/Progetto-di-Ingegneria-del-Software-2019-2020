package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

class WorkerTest {
    Player player;
    Worker worker;
    Map testMap;

    @BeforeEach
    void setUp() {
        player = new Player("Test", 22);
        testMap = new Map();
    }

    @Test
    public void checkIfNorthIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfEastIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWestIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertTrue(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[0][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[4][4];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfEastCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[0][4];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWestCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[2][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[0][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[3][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[4][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[4][1];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertFalse(worker.checkMove(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCantGoUp() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        nextCellWorker.setLevel(2);
        assertFalse(worker.checkMove(nextCellWorker));
    }

    @Test
    public void checkIfNextCellIsOccupied() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        nextCellWorker.setIsOccupied(true);
        assertFalse(worker.checkMove(nextCellWorker));
    }

    @Test
    public void checkCorrectMove() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.move(nextCellWorker);
        assertEquals(nextCellWorker, worker.getCurrentWorkerCell());
    }

    @Test
    public void checkIfWorkerOccupiesCell() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.move(nextCellWorker);
        assertTrue(worker.getCurrentWorkerCell().getIsOccupied());
    }

    @Test
    public void checkIfOldCellIsEmpty() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.move(nextCellWorker);
        assertFalse(baseCellWorker.getIsOccupied());
    }

    @Test
    public void checkCorrectBuild() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        Cell nextCellWorker = testMap.getGrid()[1][2];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.build(nextCellWorker);
        assertEquals(1, nextCellWorker.getLevel());
    }

    @Test
    public void checkCorrectWinCondition() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[0][1];
        worker = new Worker(player);
        worker.setCurrentWorkerCell(baseCellWorker);
        baseCellWorker.setLevel(2);
        nextCellWorker.setLevel(3);
        worker.move(nextCellWorker);
        assertTrue(worker.winCondition());
    }

    @Test
    public void checkIfWorkerCantMove() {
        worker = new Worker(player);
        Cell baseCellWorker = testMap.getGrid()[1][1];
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextCellWorker = testMap.getGrid()[0][0];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[0][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[0][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[1][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[2][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[2][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[2][0];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[1][0];
        nextCellWorker.setLevel(4);
        assertFalse(worker.checkSurroundingCells(testMap));
    }

    @Test
    public void checkIfWorkerCanMove() {
        worker = new Worker(player);
        Cell baseCellWorker = testMap.getGrid()[1][1];
        worker.setCurrentWorkerCell(baseCellWorker);
        Cell nextCellWorker = testMap.getGrid()[0][0];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[0][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[0][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[1][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[2][2];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[2][1];
        nextCellWorker.setLevel(4);
        nextCellWorker = testMap.getGrid()[2][0];
        nextCellWorker.setLevel(4);
        assertTrue(worker.checkSurroundingCells(testMap));
    }

    @Test
    public void checkTurn() {
        worker = new Worker(player);
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCell = testMap.getGrid()[0][1];
        worker.setCurrentWorkerCell(baseCellWorker);
        worker.turn(nextCell);
        assertEquals(nextCell, worker.getCurrentWorkerCell());
        nextCell = testMap.getGrid()[0][0];
        worker.turn(nextCell);
        assertEquals(1, nextCell.getLevel());
    }
}