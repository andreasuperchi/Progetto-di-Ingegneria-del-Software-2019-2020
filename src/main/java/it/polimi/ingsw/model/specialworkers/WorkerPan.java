package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerPan extends Worker {

    public WorkerPan() {
        super();
    }


    /**
     * add a win condition that allows the player to win if he goes down
     * two levels from the starting level
     *
     * @return
     */
    @Override
    public boolean winCondition() {
        if (newLevel == 3 && oldLevel == 2 || newLevel == oldLevel - 2) {
            return true;
        } else {
            return false;
        }
    }
}
