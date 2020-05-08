package it.polimi.ingsw.model;

import java.util.Objects;

public class Cell {
    private int rowNumber;
    private int columnNumber;
    private boolean isOccupied;
    private int level;
    private Worker thisWorker;

    public Cell(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        level = 0;
        isOccupied = false;
        thisWorker = null;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        if (level == 4) {
            Model.getMap().setCompletedTowers(Model.getMap().getCompletedTowers() + 1);
            isOccupied = true;
        }
        this.level = level;
    }

    public Worker getThisWorker() {
        return thisWorker;
    }

    public void setThisWorker(Worker thisWorker) {
        this.thisWorker = thisWorker;
    }

    @Override
    public String toString() {
        String string;
        if (isOccupied && level != 4) {
            string = thisWorker.symbol;
        } else {
            string = " ";
        }
        return string;
    }
}
