package it.polimi.ingsw.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CellTest {
    Cell cell;
    Worker worker;

    @BeforeEach
    void setUp() {
        cell = new Cell(1, 1);
        worker = new Worker();
        worker.symbol = "@A";
    }

    @Test
    public void testToStringEmpty() {
        cell.setLevel(2);
        System.out.println(cell.toString());
    }

    @Test
    public void testToString() {
        cell.setLevel(2);
        worker.setCurrentWorkerCell(cell);
        System.out.println(cell.toString());
    }
}