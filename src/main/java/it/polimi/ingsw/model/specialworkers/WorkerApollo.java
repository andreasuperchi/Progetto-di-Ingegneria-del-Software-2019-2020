package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerApollo extends Worker {

    public WorkerApollo() {
        super();
    }

    @Override
    public void move(Cell nextWorkerCell) {
        if (nextWorkerCell.getIsOccupied() && nextWorkerCell.getLevel() != 4) {
            nextWorkerCell.getThisWorker().move(this.getCurrentWorkerCell());
        }
        super.move(nextWorkerCell);
    }
}
