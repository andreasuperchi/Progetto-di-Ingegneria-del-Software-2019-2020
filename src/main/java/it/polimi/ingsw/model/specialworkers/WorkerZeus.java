package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerZeus extends Worker {

    private boolean canWin = true;

    public WorkerZeus() {
        super();
        hasSpecialPower = false;
    }

    //tengo traccia se costruisce sotto di se -> non potrà vincere
    @Override
    public boolean checkBuild(Cell nextWorkerCell) {
        if (nextWorkerCell.equals(currentWorkerCell)) {
            canWin = false;
            return true;
        } else {
            canWin = true;
            return !nextWorkerCell.getIsOccupied();
        }
    }

    @Override
    public boolean winCondition() {
        if (newLevel == 3 && oldLevel == 2 && canWin == true) {
            return true;
        } else {
            return false;
        }
    }

    // il potere speciale è già incluso
    @Override
    public void specialPower(Cell nextWorkerCell) {
        throw new IllegalArgumentException();
    }
}
