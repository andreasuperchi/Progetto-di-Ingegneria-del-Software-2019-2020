package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorkerTest {
    Player player;
    Worker worker;
    Map testMap;

    @BeforeEach
    void setUp() {
        player = new Player("Test", 22, "blue");
        testMap = new Map();
    }

    @Test
    public void checkIfNorthIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfSouthIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfEastIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfWestIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestIsValid() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertEquals(true, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfNorthCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[0][0];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfSouthCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[4][4];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfEastCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[0][4];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.EAST);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfWestCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[2][0];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.WEST);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfNorthEastCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[0][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_EAST);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfNorthWestCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[3][0];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.NORTH_WEST);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfSouthEastCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[4][2];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_EAST);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfSouthWestCellIsNotValid() {
        Cell baseCellWorker = testMap.getGrid()[4][1];
        worker = new Worker(player, baseCellWorker);
        Cell nextWorkerCell = testMap.getNextWorkerCell(worker.getCurrentWorkerCell(), Direction.SOUTH_WEST);
        assertEquals(false, worker.checkChoice(nextWorkerCell));
    }

    @Test
    public void checkIfWorkerCantGoUp() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player, baseCellWorker);
        nextCellWorker.setLevel(2);
        assertEquals(false, worker.checkChoice(nextCellWorker));
    }

    @Test
    public void checkIfNextCellIsOccupied() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player, baseCellWorker);
        nextCellWorker.setIsOccupied(true);
        assertEquals(false, worker.checkChoice(nextCellWorker));
    }

    @Test
    public void checkCorrectMove() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player, baseCellWorker);
        worker.move(nextCellWorker);
        assertEquals(nextCellWorker, worker.getCurrentWorkerCell());
    }

    @Test
    public void checkIfWorkerOccupiesCell() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player, baseCellWorker);
        worker.move(nextCellWorker);
        assertEquals(true, worker.getCurrentWorkerCell().getIsOccupied());
    }

    @Test
    public void checkIfOldCellIsEmpty() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[1][0];
        worker = new Worker(player, baseCellWorker);
        worker.move(nextCellWorker);
        assertEquals(false, baseCellWorker.getIsOccupied());
    }

    @Test
    public void checkCorrectBuild() {
        Cell baseCellWorker = testMap.getGrid()[2][2];
        Cell nextCellWorker = testMap.getGrid()[1][2];
        worker = new Worker(player, baseCellWorker);
        worker.build(nextCellWorker);
        assertEquals(1, nextCellWorker.getLevel());
    }

    @Test
    public void checkCorrectWinCondition() {
        Cell baseCellWorker = testMap.getGrid()[1][1];
        Cell nextCellWorker = testMap.getGrid()[0][1];
        worker = new Worker(player, baseCellWorker);
        baseCellWorker.setLevel(2);
        nextCellWorker.setLevel(3);
        worker.move(nextCellWorker);
        assertEquals(true, worker.winCondition());

    }
}