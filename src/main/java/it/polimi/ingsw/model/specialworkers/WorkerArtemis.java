package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Outcome;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerArtemis extends Worker {
    Cell oldPosition;


    public WorkerArtemis() {
        super();
        this.hasSpecialPower = true;
    }


    @Override
    public void move(Cell nextWorkerCell) {
        oldPosition = currentWorkerCell;
        super.move(nextWorkerCell);
    }


    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (!hasMoved || hasBuilt){
            throw new IllegalArgumentException();
        }
        else{
            if (nextWorkerCell.equals(oldPosition)){
                throw new IllegalArgumentException();
            }
            else{
                move(nextWorkerCell);
                hasUsedSpecialPower = true;
            }
        }
    }
}
