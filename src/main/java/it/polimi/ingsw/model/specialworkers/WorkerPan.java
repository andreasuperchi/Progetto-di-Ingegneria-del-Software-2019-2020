package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Worker;

public class WorkerPan extends Worker {

    public WorkerPan() {
        super();
    }


    /**
     * adds a win condition that allows the player to win if he goes down
     * two levels from the starting level
     *
     * @return true if the worker has gone down two levels from the starting point
     */
    @Override
    public boolean winCondition() {
        return newLevel == 3 && oldLevel == 2 || newLevel == oldLevel - 2;
    }
}
