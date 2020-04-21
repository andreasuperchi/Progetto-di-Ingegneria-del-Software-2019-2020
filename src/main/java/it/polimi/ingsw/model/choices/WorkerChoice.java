package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.choices.PlayerChoice;

public class WorkerChoice extends PlayerChoice {
    private int workerNumber;

    public WorkerChoice(Player player, int workerNumber) {
        this.player = player;
        this.workerNumber = workerNumber;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }
}
