package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

public class WorkerApollo extends Worker {

    /**
     * builds a new worker using his super-class constructor and sets to sets to false
     * the special power availability. In fact Apollo has no special power.
     */
    public WorkerApollo() {
        super();
        hasSpecialPower = false;
    }

    /**
     * an utility method that checks if the specified cell is not occupied by another WorkerApollo
     *
     * @param opponentsCell is the cell that is going to be checked
     * @return true if the cell is not occupied by another WorkerApollo, false otherwise
     */
    public boolean checkNotBothApollos(Cell opponentsCell) {
        return !(opponentsCell.getThisWorker() instanceof WorkerApollo);
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
                ((nextWorkerCell.getLevel() != 4 && levelDiff <= 0 && checkNotBothApollos(nextWorkerCell)) ||
                        (nextWorkerCell.getLevel() != 4 && levelDiff == 1 && canGoUp && checkNotBothApollos(nextWorkerCell)));
    }

    /**
     * moves the worker from its current cell to a specified cell. In this case, if the specified cell
     * is occupied by a worker that is not of type WorkerApollo, this worker and the worker that occupies
     * the specified cell are going to be switched.
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
    @Override
    public void move(Cell nextWorkerCell) {
        Cell tempCell = new Cell(0, 0);
        Cell oldCell = this.currentWorkerCell;

        if (!checkMove(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            if (nextWorkerCell.getIsOccupied() && nextWorkerCell.getLevel() != 4) {
                this.setCurrentWorkerCell(tempCell);
                oldCell.setIsOccupied(false);
                nextWorkerCell.getThisWorker().setCurrentWorkerCell(oldCell);
                nextWorkerCell.setIsOccupied(false);
            }
            super.move(nextWorkerCell);
        }
    }
}
