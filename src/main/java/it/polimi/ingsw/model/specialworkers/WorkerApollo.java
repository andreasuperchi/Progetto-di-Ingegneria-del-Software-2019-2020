package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

public class WorkerApollo extends Worker {

    public WorkerApollo() {
        super();
        hasSpecialPower = false;
    }

    public boolean checkNotBothApollos(Cell opponentsCell) {
        return !(opponentsCell.getThisWorker() instanceof WorkerApollo);
    }

    @Override
    public void setCurrentWorkerCell(Cell currentWorkerCell) {
        if (currentWorkerCell.getIsOccupied() && currentWorkerCell.getLevel() == 4) {
            throw new IllegalArgumentException();
        } else {
            currentWorkerCell.setIsOccupied(true);
            currentWorkerCell.setThisWorker(this);
            this.currentWorkerCell = currentWorkerCell;
        }
    }

    @Override
    public boolean checkMove(Cell nextWorkerCell) {
        int levelDiff = nextWorkerCell.getLevel() - this.getCurrentWorkerCell().getLevel();

        return !(this.getCurrentWorkerCell().equals(nextWorkerCell)) &&
                ((nextWorkerCell.getLevel() != 4 && levelDiff <= 0 && checkNotBothApollos(nextWorkerCell)) ||
                        (nextWorkerCell.getLevel() != 4 && levelDiff == 1 && canGoUp && checkNotBothApollos(nextWorkerCell)));
    }

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
            }
            super.move(nextWorkerCell);
        }
    }
}
