package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerHephaestus extends Worker {


    private Cell builtCell;

    /**
     * Builds a new worker using his super-class constructor and sets to true
     * the special power availability.
     */
    public WorkerHephaestus() {
        super();
        hasSpecialPower = true;
    }


    /**
     * Checks if the worker can use the special power
     *
     * @return a boolean value that indicates if special power can be used
     */
    @Override
    public boolean canUseSpecialPower() {
        return hasMoved && hasBuilt;
    }

    /**
     * Do the Override of move from the class Worker.
     * add the following statement:
     * save the cell where the worker has built
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be build
     */
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

    /**
     * Do the Override of move from the class Worker.
     * add the following check:
     * check if the cell where worker want to use special power is the same of builtCell and
     * that the cell level is < 3 (worker Hephaestus can't build a dome)
     *
     * @param cell is the cell where the special power is to apply
     */
    @Override
    //cell è la cella dove voglio costruire il blocco addizionale (non cupola)
    public void specialPower(Cell cell) {
        if (builtCell == null || !cell.equals(builtCell)) {
            throw new IllegalArgumentException();
        } else if (cell.getLevel() == 3) {
            throw new IllegalArgumentException();
        } else {
            build(cell);
            builtCell=null;     //così non puo riusare 2 volte di fila il potere speciale
        }
    }
}
