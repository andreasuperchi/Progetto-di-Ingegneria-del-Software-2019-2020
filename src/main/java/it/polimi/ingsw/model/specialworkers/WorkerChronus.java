package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerChronus extends Worker {

    public WorkerChronus() {
        super();
    }

    @Override
    public boolean winCondition() {
        return (getNewLevel() == 3 && getOldLevel() == 2) || (Model.getMap().getCompletedTowers() >= 5);
    }
}
