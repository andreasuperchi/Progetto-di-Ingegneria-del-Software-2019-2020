package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.Worker;

public class WorkerAtlas extends Worker {

    private boolean wantsToBuildADome;

    public WorkerAtlas(Player player) {
        super(player);
    }

    @Override
    public void build(Cell nextWorkerCell) {
        if (wantsToBuildADome) {
            nextWorkerCell.setLevel(4);
        } else {
            super.build(nextWorkerCell);
        }
    }

}
