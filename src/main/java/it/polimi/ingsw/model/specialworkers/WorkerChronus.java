package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerChronus extends Worker {

    /**
     * builds a new WorkerChronus using its super-class
     * constructor and setting the availability of the special
     * power to false (Chronus has no special power)
     */
    public WorkerChronus() {
        super();
        hasSpecialPower = false;
    }

    /**
     * specifies the win conditions of Chronus
     *
     * @return true if the "standard" win conditions are verified or if the completed
     * towers in the map are 5
     */
    @Override
    public boolean winCondition() {
        return (getNewLevel() == 3 && getOldLevel() == 2) || (Model.getMap().getCompletedTowers() >= 5);
    }
}
