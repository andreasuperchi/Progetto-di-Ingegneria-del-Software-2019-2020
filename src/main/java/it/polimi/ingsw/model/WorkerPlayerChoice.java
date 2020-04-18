package it.polimi.ingsw.model;

public class WorkerPlayerChoice extends PlayerChoice {
    private int workerNumber;

    public WorkerPlayerChoice(Player player, int workerNumber) {
        this.player = player;
        this.workerNumber = workerNumber;
    }

    public int getWorkerNumber() {
        return workerNumber;
    }
}
