package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerDemeter extends Worker {
    Cell oldBuildPosition;

    public WorkerDemeter() {
        super();
        this.hasSpecialPower = true;
    }

    @Override
    public void build(Cell nextWorkerCell) {
        oldBuildPosition = nextWorkerCell;
        super.build(nextWorkerCell);
    }

    @Override
    public boolean canUseSpecialPower() {
        if (!hasMoved || !hasBuilt) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (nextWorkerCell.equals(oldBuildPosition)) {
            throw new IllegalArgumentException();
        } else {
            build(nextWorkerCell);
            hasUsedSpecialPower = true;
        }
    }


}
