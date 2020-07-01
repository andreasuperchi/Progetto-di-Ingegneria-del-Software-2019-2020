package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerTriton extends Worker {

    /**
     * builds a new WorkerTriton using its super-class constructor and
     * setting the availability of the special power to true and setting
     * the fact that this special power hasn't been used yet
     */
    public WorkerTriton() {
        super();
        hasSpecialPower = true;
        hasUsedSpecialPower = false;
    }

    /**
     * verifies if the nextWorkerCell is a cell on the perimeter
     *
     * @param nextWorkerCell is the cell that is going to be analyzed
     * @return true if the specified cell in on the perimeter
     */
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

    /**
     * checks if this worker can use his special power
     *
     * @return true if this worker hasn't moved yet
     */
    @Override
    public boolean canUseSpecialPower() {
        return !hasMoved;
    }

    /**
     * does the exact same thing of its super-class method, but sets to false
     * the flag hasUsedSpecialPower if the nextWorkerCell is on the perimeter of the board
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
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
            this.hasUsedSpecialPower = !verifyPerimeter(this.currentWorkerCell);  // se la cella era perimetrale posso muovermi ancora
            hasMoved = hasUsedSpecialPower;
        }
    }
}
