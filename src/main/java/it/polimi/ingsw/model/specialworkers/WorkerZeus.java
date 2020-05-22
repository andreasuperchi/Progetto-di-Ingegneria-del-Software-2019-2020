package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerZeus extends Worker {

    private boolean canWin = true;

    public WorkerZeus() {
        super();
        hasSpecialPower = true;
    }

    @Override
    public boolean canUseSpecialPower() {
        return hasMoved && !hasBuilt;
    }

    @Override
    public boolean winCondition() {
        if (newLevel == 3 && oldLevel == 2 && canWin == true) {
            return true;
        } else {
            return false;
        }
    }

    // costruisce sotto di se
    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (!nextWorkerCell.equals(currentWorkerCell)){
            throw new IllegalArgumentException();
        }
        else{
            if (nextWorkerCell.getLevel() == 3) {
                //non puo costruirsi una cupola sotto di se
                throw new IllegalArgumentException();
            } else {
                nextWorkerCell.setLevel(nextWorkerCell.getLevel() + 1);
                this.hasBuilt = true;
                //se è salito al livello 3, non può vincere
                if (nextWorkerCell.getLevel()==3){
                    canWin=false;
                }
            }

        }
    }
}
