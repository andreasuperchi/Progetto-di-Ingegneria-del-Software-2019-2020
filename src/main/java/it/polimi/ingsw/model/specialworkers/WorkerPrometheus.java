package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerPrometheus extends Worker {

    /**
     * builds a new worker using his super-class constructor and sets to sets to true
     * the special power availability.
     */
    public WorkerPrometheus() {
        super();
        hasSpecialPower = true;
    }

    /**
     * Calls the move method of the super class and set the canGoUp value to true
     * if it was false
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
    @Override
    public void move(Cell nextWorkerCell) {
        super.move(nextWorkerCell);
        if (!canGoUp) {
            canGoUp = true;
        }
    }

    /**
     * Checks if the worker can use his special power
     *
     * @return true if the worker hasn't moved yet
     */
    @Override
    public boolean canUseSpecialPower() {
        return !hasMoved;
    }

    /**
     * Uses the worker special power that consist in an additional build action
     * before the move action. This special power does not allow the worker to go up
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
    @Override
    public void specialPower(Cell nextWorkerCell) {
        build(nextWorkerCell);
        canGoUp = false;
    }
}
