package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerDemeter extends Worker {
    Cell oldBuildPosition;


    /**
     * builds a new worker using his super-class constructor and sets to sets to true
     * the special power availability.
     */
    public WorkerDemeter() {
        super();
        this.hasSpecialPower = true;
    }

    /**
     * Save the position where the worker want to build and call the build method of the super class
     *
     * @param nextWorkerCell is the specified cell
     */
    @Override
    public void build(Cell nextWorkerCell) {
        oldBuildPosition = nextWorkerCell;
        super.build(nextWorkerCell);
    }

    /**
     * Check if the worker can use his special power
     *
     * @return true if the worker has both moved and built
     */
    @Override
    public boolean canUseSpecialPower() {
        return hasMoved && hasBuilt;
    }


    /**
     * Uses the special power of the worker that consists in one additional build action. If the nextWorkerCell is equal to oldPosition
     * throws an IllegalArgumentException
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
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
