package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerAthena extends Worker {

    /**
     * Builds a new worker using his super-class constructor and sets to false
     * the special power availability.
     */
    public WorkerAthena() {
        super();
        hasSpecialPower = false;
    }


    /**
     * Do the Override of move from the class Worker.
     * add the following condition:
     * if worker Athena is go up, the others workers can't go up in their turn.
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
    @Override
    public void move(Cell nextWorkerCell) {
        setCanGoUp(true);                       // setto canGoUp a true per tutti

        if (!checkMove(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            oldLevel = getCurrentWorkerCell().getLevel();
            getCurrentWorkerCell().setIsOccupied(false);
            getCurrentWorkerCell().setThisWorker(null);
            setCurrentWorkerCell(nextWorkerCell);
            newLevel = getCurrentWorkerCell().getLevel();
            getCurrentWorkerCell().setIsOccupied(true);
            this.hasMoved = true;
        }
        //se WorkerAthena è salito dico che gli avversari non potranno salire nei loro turni
        if (getNewLevel() > getOldLevel()) {
            setCanGoUp(false);
            hasUsedSpecialPower = true;
        }

    }

}
