package it.polimi.ingsw.model;

public class Cell {
    private final int rowNumber;
    private final int columnNumber;
    private boolean isOccupied;
    private int level;
    private Worker thisWorker;

    /**
     * creates a new cell, initializing his indexes, his level and specifying the fact that no one occupies it and that
     * is not associated with any worker
     *
     * @param rowNumber    is the number of the row that is going to be associated with this cell
     * @param columnNumber is the number of the column that is going to be associated with this cell
     */
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

    public int getColumnNumber() {
        return columnNumber;
    }

    public boolean getIsOccupied() {
        return isOccupied;
    }

    public void setIsOccupied(boolean occupied) {
        this.isOccupied = occupied;
    }

    public int getLevel() {
        return level;
    }

    /**
     * sets the level of the cell.
     * If the new level is 4, increments the number of the total completed towers.
     * Also permanently sets this cell as occupied
     *
     * @param level is the level that is going to be set
     */
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

    /**
     * is used to print each cell in the toString method of Map
     *
     * @return the String representation of this cell, with a precise formatting
     */
    @Override
    public String toString() {
        String string;

        if (isOccupied && level != 4) {
            string = thisWorker.symbol;
        } else if (level == 4) {
            string = "  ";
        } else {
            string = " ";
        }
        return string;
    }
}
