package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerMinotaur extends Worker {

    /**
     * builds a new WorkerMinotaur using its super-class constructor
     * and setting the availability of the special power to false
     * (Minotaur has no special power)
     */
    public WorkerMinotaur() {
        super();
        hasSpecialPower = false;
    }

    /**
     * check if the specified cell is occupied by another WorkerMinotaur
     *
     * @param opponentsCell is the cell that is going to be checked
     * @return true if the specified cell is not occupied by another WorkerMinotaur
     */
    public boolean checkNotBothMinotaurs(Cell opponentsCell) {
        return !(opponentsCell.getThisWorker() instanceof WorkerMinotaur);
    }

    /**
     * overrides its super-class method removing the bond of having the specified
     * cell occupied by a worker
     *
     * @param currentWorkerCell is the Cell where the Worker is going to be placed
     */
    @Override
    public void setCurrentWorkerCell(Cell currentWorkerCell) {
        if (currentWorkerCell.getLevel() == 4) {
            throw new IllegalArgumentException();
        } else {
            currentWorkerCell.setIsOccupied(true);
            currentWorkerCell.setThisWorker(this);
            this.currentWorkerCell = currentWorkerCell;
        }
    }

    /**
     * retrieves the next cell on the line that passes through the two specified cells
     *
     * @param baseWorkerCell is the first specified cell (it's the current cell of the worker)
     * @param nextWorkerCell is the second specified cell (it's the cell specified for the movement action)
     * @return the cell where, if the nextWorkerCell is occupied, the worker that occupies the nextWorkerCell
     * is going to be moved
     */
    public Cell getCellInThatDirection(Cell baseWorkerCell, Cell nextWorkerCell) {  //restituisce la cella successiva a nextWorkerCell nella Direzione scelta
        int[] cellsDifference = new int[2];
        cellsDifference[0] = baseWorkerCell.getRowNumber() - nextWorkerCell.getRowNumber();
        cellsDifference[1] = baseWorkerCell.getColumnNumber() - nextWorkerCell.getColumnNumber();
        if (cellsDifference[0] == 0 && cellsDifference[1] == 0) {
            throw new IllegalArgumentException();
        } else {
            if (cellsDifference[0] == 1 && cellsDifference[1] == -1) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.NORTH_EAST);
            } else if (cellsDifference[0] == 0 && cellsDifference[1] == -1) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.EAST);
            } else if (cellsDifference[0] == -1 && cellsDifference[1] == -1) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.SOUTH_EAST);
            } else if (cellsDifference[0] == -1 && cellsDifference[1] == 0) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.SOUTH);
            } else if (cellsDifference[0] == -1 && cellsDifference[1] == 1) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.SOUTH_WEST);
            } else if (cellsDifference[0] == 0 && cellsDifference[1] == 1) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.WEST);
            } else if (cellsDifference[0] == 1 && cellsDifference[1] == 1) {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.NORTH_WEST);
            } else {
                return Model.getMap().getNextWorkerCell(nextWorkerCell, Direction.NORTH);
            }
        }
    }

    /**
     * checks if this worker can move to the specified cell. Compared to its super-class method,
     * this one hasn't got the bond of having the cell not occupied
     *
     * @param nextWorkerCell is the Cell where the Worker wants to move
     * @return true if the worker can move to the specified cell, meaning that the maximum level
     * difference between the starting cell and the specified cell is 1, that the specified cell
     * is not the starting cell, that the cell is not occupied by another WorkerApollo and that the
     * cell is not at level 4
     */
    @Override
    public boolean checkMove(Cell nextWorkerCell) {
        int levelDiff = nextWorkerCell.getLevel() - this.getCurrentWorkerCell().getLevel();

        return !(this.getCurrentWorkerCell().equals(nextWorkerCell)) &&
                ((nextWorkerCell.getLevel() != 4 && levelDiff <= 0 && checkNotBothMinotaurs(nextWorkerCell)) ||
                        (nextWorkerCell.getLevel() != 4 && levelDiff == 1 && canGoUp && checkNotBothMinotaurs(nextWorkerCell)));
    }

    /**
     * if the nextWorkerCell is empty calls the move method of the super-class, "forces back"
     * the worker that occupies the nextWorkerCell otherwise
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
    @Override
    public void move(Cell nextWorkerCell) {
        Cell otherCell = getCellInThatDirection(this.currentWorkerCell, nextWorkerCell);

        if (!checkMove(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            if (nextWorkerCell.getIsOccupied() && !otherCell.getIsOccupied()) {
                nextWorkerCell.getThisWorker().setCurrentWorkerCell(otherCell);
            }
            super.move(nextWorkerCell);
        }
    }
}
