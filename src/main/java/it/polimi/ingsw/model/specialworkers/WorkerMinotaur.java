package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerMinotaur extends Worker {

    private boolean forceBack;

    public boolean getForceBack() {
        return forceBack;
    }

    public void setForceBack(boolean forceBack) {
        this.forceBack = forceBack;
    }

    public WorkerMinotaur() {
        super();
    }

    public Cell getCellInThatDirection(Cell baseWorkerCell, Cell nextWorkerCell) {  //restituisce la cella successiva a nextWorkerCell nella Direzione scelta
        int[] cellsDifference = new int[2];
        cellsDifference[0] = baseWorkerCell.getRowNumber() - nextWorkerCell.getRowNumber();
        cellsDifference[1] = baseWorkerCell.getColumnNumber() - nextWorkerCell.getColumnNumber();
        if (cellsDifference[0] == 0 && cellsDifference[1] == 0) {
            return null;
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
        if (nextWorkerCell == null || nextWorkerCell.getLevel() - getCurrentWorkerCell().getLevel() > 1 ||
                (nextWorkerCell.getThisWorker().equals(Model.getCurrentPlayer().getWorkers()[0]) ||
                        nextWorkerCell.getThisWorker().equals(Model.getCurrentPlayer().getWorkers()[1]))) {
            return false;
        } else if (nextWorkerCell.getLevel() - getCurrentWorkerCell().getLevel() == 1 && getCanGoUp()) {   //controllo per Athena
            return false;
        } else if ((nextWorkerCell.getIsOccupied() && nextWorkerCell.getLevel() != 4) &&
                getCellInThatDirection(this.getCurrentWorkerCell(), nextWorkerCell) != null &&
                !getCellInThatDirection(this.getCurrentWorkerCell(), nextWorkerCell).getIsOccupied()) {
            forceBack = true;
            return true;
        } else {
            forceBack = false;
            return true;
        }
    }

    @Override
    public void move(Cell nextWorkerCell) {
        if (forceBack) {
            nextWorkerCell.getThisWorker().move(getCellInThatDirection(this.getCurrentWorkerCell(), nextWorkerCell));
        }
        super.move(nextWorkerCell);
    }
}
