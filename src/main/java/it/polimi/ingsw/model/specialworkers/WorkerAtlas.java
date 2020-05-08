package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

public class WorkerAtlas extends Worker {

    public WorkerAtlas() {
        super();
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (nextWorkerCell.getLevel() == 4) {
            throw new IllegalArgumentException();
        } else {
            nextWorkerCell.setLevel(4);
        }
    }
}
