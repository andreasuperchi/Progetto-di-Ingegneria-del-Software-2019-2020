package it.polimi.ingsw.model;

public class Cell {
    private int rowNumber;
    private int columnNumber;
    private boolean isOccupied;
    private int level;

    public Cell(int rowNumber, int columnNumber) {
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
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

    public int getLevel() { return level; }

    public void setLevel(int level) { this.level = level; }



}
