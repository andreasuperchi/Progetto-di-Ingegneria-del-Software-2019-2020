package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Worker;

public class WorkerAtlas extends Worker {

    /**
     * builds a new WorkerAtlas using its super-class constructor and
     * setting the availability of the special power to true and setting
     * the fact that this special power hasn't been used yet
     */
    public WorkerAtlas() {
        super();
        hasSpecialPower = true;
        hasUsedSpecialPower = false;
    }

    /**
     * checks if the worker can use his special power
     *
     * @return true if the worker has already moved but still hasn't built
     */
    @Override
    public boolean canUseSpecialPower() {
        return hasMoved && !hasBuilt;
    }

    /**
     * specifies the special power of WorkerAtlas. If the checkBuild method of its super-class
     * returns true, sets the level of the specified cell to 4 (builds a dome) and sets the flag
     * corresponding to having built to true
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (!checkBuild(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            nextWorkerCell.setLevel(4);
            hasUsedSpecialPower = true;
            hasBuilt = true;
        }
    }
}
