package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

public class WorkerAtlas extends Worker {

    public WorkerAtlas() {
        super();
        hasSpecialPower = true;
        hasUsedSpecialPower = false;
    }

    @Override
    public boolean checkBuild(Cell nextWorkerCell) {
        return !nextWorkerCell.getIsOccupied() && !(nextWorkerCell.equals(this.currentWorkerCell));
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (nextWorkerCell.getLevel() == 4 || !this.hasMoved) {
            throw new IllegalArgumentException();
        } else {
            nextWorkerCell.setLevel(4);
            hasUsedSpecialPower = true;
            hasBuilt = true;
        }
    }
}
