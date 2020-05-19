package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerTriton extends Worker {

    public WorkerTriton() {
        super();
        hasSpecialPower = true;
        hasUsedSpecialPower = false;
    }

    public boolean verifyPerimeter(Cell nextWorkerCell) {
        for (Direction d : Direction.values()) {
            try {
                Model.getMap().getNextWorkerCell(nextWorkerCell, d);
            } catch (ArrayIndexOutOfBoundsException e) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canUseSpecialPower() {
        return !hasMoved;
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (!checkMove(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            oldLevel = getCurrentWorkerCell().getLevel();
            getCurrentWorkerCell().setIsOccupied(false);
            setCurrentWorkerCell(nextWorkerCell);
            newLevel = getCurrentWorkerCell().getLevel();
            getCurrentWorkerCell().setIsOccupied(true);
            this.hasUsedSpecialPower = verifyPerimeter(nextWorkerCell);  // se la cella era perimetrale posso muovermi ancora
        }
    }
}
