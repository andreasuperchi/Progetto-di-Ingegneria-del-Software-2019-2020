package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerAthena extends Worker {

    public WorkerAthena() {
        super();
        hasSpecialPower = false;
    }

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

    // il potere speciale è già incluso
    @Override
    public void specialPower(Cell nextWorkerCell) {
        throw new IllegalArgumentException();
    }
}
