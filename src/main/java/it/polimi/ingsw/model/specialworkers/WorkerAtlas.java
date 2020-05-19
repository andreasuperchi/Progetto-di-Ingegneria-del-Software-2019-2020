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
    public boolean canUseSpecialPower() {
        return hasMoved && !hasBuilt;
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (!checkBuild(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            nextWorkerCell.setLevel(4);
            hasUsedSpecialPower = true;
            hasBuilt = true;
        }
    }
}
