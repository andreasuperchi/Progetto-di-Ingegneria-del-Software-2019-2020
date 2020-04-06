package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerChronus extends Worker {

    public WorkerChronus(Player player) {
        super(player);
    }

    @Override
    public boolean winCondition() {
        if ((getNewLevel() == 3 && getOldLevel() == 2) || (Model.getMap().getCompletedTowers() >= 5)) {
            return true;
        } else {
            return false;
        }
    }
}
