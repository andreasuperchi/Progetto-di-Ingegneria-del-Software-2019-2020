package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Outcome;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerArtemis extends Worker {
    Cell oldPosition;

    /**
     * builds a new worker using his super-class constructor and sets to sets to true
     * the special power availability.
     */
    public WorkerArtemis() {
        super();
        this.hasSpecialPower = true;
    }

    /**
     * Saves the old position of the Worker and calls the move method of the super-class
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
    @Override
    public void move(Cell nextWorkerCell) {
        oldPosition = currentWorkerCell;
        super.move(nextWorkerCell);
    }

    /**
     * Checks if the worker can use his special power
     *
     * @return true if the worker has moved but hasn't built yet
     */
    @Override
    public boolean canUseSpecialPower() {
        return hasMoved && !hasBuilt;
    }

    /**
     * Uses the special power that consists in one additional move action. If nextWorkerCell is equal to oldPosition
     * throws an IllegalArgumentException
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (nextWorkerCell.equals(oldPosition)) {
            throw new IllegalArgumentException();
        } else {
            move(nextWorkerCell);
            hasUsedSpecialPower = true;
        }
    }

}
