package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerApollo extends Worker {

    public WorkerApollo() {
        super();
    }

    @Override
    public boolean checkMove(Cell nextWorkerCell) {
        //controllo per Athena
        if (nextWorkerCell == null || nextWorkerCell.getLevel() - getCurrentWorkerCell().getLevel() > 1 || nextWorkerCell.getIsOccupied() ||
                (nextWorkerCell.getThisWorker().equals(Model.getCurrentPlayer().getWorkers()[0]) ||
                        nextWorkerCell.getThisWorker().equals(Model.getCurrentPlayer().getWorkers()[1]))) {
            return false;
        } else return nextWorkerCell.getLevel() - getCurrentWorkerCell().getLevel() != 1 || getCanGoUp();
    }

    @Override
    public void move(Cell nextWorkerCell) {
        if (nextWorkerCell.getIsOccupied() && nextWorkerCell.getLevel() != 4) {
            nextWorkerCell.getThisWorker().move(this.getCurrentWorkerCell());
        }
        super.move(nextWorkerCell);
    }
}
