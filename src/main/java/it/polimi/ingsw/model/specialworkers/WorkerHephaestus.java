package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerHephaestus extends Worker {

    public WorkerHephaestus() {
        super();
    }


    private Cell builtCell;

    @Override
    public void build(Cell nextWorkerCell) {
        if (!checkBuild(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            if (nextWorkerCell.getLevel() == 3) {
                nextWorkerCell.setLevel(4);
                Model.getMap().setCompletedTowers(Model.getMap().getCompletedTowers() + 1);
                nextWorkerCell.setIsOccupied(true);
            } else {
                nextWorkerCell.setLevel(nextWorkerCell.getLevel() + 1);
            }
            this.hasBuilt = true;

            //salvo la cella dove ha costruito (serve per il suo potere speciale)
            builtCell = nextWorkerCell;
        }
    }

    @Override
    //cell è la cella dove voglio costruire il blocco addizionale (non cupola)
    public void specialPower(Cell cell) {
        if (builtCell == null || !cell.equals(builtCell)) {
            throw new IllegalArgumentException();
        } else if (cell.getLevel() == 3) {
            throw new IllegalArgumentException();
        } else {
            build(cell);
        }
    }
}
