package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerMinotaur extends Worker {

    public WorkerMinotaur() {
        super();
        hasSpecialPower = false;
    }

    public boolean checkNotBothMinotaurs(Cell opponentsCell) {
        return !(opponentsCell.getThisWorker() instanceof WorkerMinotaur);
    }

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

    @Override
    public boolean checkMove(Cell nextWorkerCell) {
        int levelDiff = nextWorkerCell.getLevel() - this.getCurrentWorkerCell().getLevel();

        return !(this.getCurrentWorkerCell().equals(nextWorkerCell)) &&
                ((nextWorkerCell.getLevel() != 4 && levelDiff <= 0 && checkNotBothMinotaurs(nextWorkerCell)) ||
                        (nextWorkerCell.getLevel() != 4 && levelDiff == 1 && canGoUp && checkNotBothMinotaurs(nextWorkerCell)));
    }

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
