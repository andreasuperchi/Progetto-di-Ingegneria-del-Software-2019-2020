package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerPrometheus extends Worker {

    public WorkerPrometheus() {
        super();
        hasSpecialPower = true;
    }


    @Override
    public void move(Cell nextWorkerCell) {
        super.move(nextWorkerCell);
        if (!canGoUp) {
            canGoUp = true;
        }
    }

    @Override
    public boolean canUseSpecialPower() {
        if (hasMoved) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        build(nextWorkerCell);
        canGoUp = false;
    }
}
