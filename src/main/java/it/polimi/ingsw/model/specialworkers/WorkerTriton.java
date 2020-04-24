package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerTriton extends Worker {

    private boolean moveAnotherTime;

    public WorkerTriton() {
        super();
    }

    public boolean getMoveAnotherTime() {
        return moveAnotherTime;
    }

    public void setMoveAnotherTime(boolean moveAnotherTime) {
        this.moveAnotherTime = moveAnotherTime;
    }

    public boolean verifyPerimeter(Cell nextWorkerCell) {
        for (Direction d : Direction.values()) {
            if (Model.getMap().getNextWorkerCell(nextWorkerCell, d) == null) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean checkMove(Cell nextWorkerCell) {
        if (nextWorkerCell == null || nextWorkerCell.getLevel() - getCurrentWorkerCell().getLevel() > 1 || nextWorkerCell.getIsOccupied()) {
            return false;
        } else if (nextWorkerCell.getLevel() - getCurrentWorkerCell().getLevel() == 1 && getCanGoUp()) {   //controllo per Athena
            return false;
        } else {
            moveAnotherTime = verifyPerimeter(nextWorkerCell);
            return true;
        }
    }
}
