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
        if (canGoUp = false) {
            canGoUp = true;
        }
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (!hasMoved) {
            build(nextWorkerCell);
            canGoUp = false;
        } else {
            throw new IllegalArgumentException();
        }
    }
}
