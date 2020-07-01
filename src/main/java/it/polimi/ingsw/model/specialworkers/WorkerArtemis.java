package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Outcome;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerArtemis extends Worker {
    Cell oldPosition;


    public WorkerArtemis() {
        super();
        this.hasSpecialPower = true;
    }

    /**
     * Save the old position of the Worker and call the move method of the super class
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
    @Override
    public void move(Cell nextWorkerCell) {
        oldPosition = currentWorkerCell;
        super.move(nextWorkerCell);
    }

    /**
     * Check if the worker can use his special power
     *
     * @return
     */
    @Override
    public boolean canUseSpecialPower() {
        if (!hasMoved || hasBuilt) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Use the special power that consist in one additional move action. If nextWorkerCell is equal to oldPosition
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
