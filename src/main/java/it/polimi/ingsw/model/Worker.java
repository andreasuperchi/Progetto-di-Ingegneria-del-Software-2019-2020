package it.polimi.ingsw.model;

public class Worker {
    private String playerName;
    private Cell currentCellWorker;
    private boolean canGoUp;
    private int oldLevel;
    private int newLevel;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Cell getCurrentWorkerCell() {
        return currentCellWorker;
    }

    public void setCurrentWorkerCell(Cell currentWorkerCell) {
        this.currentCellWorker = currentWorkerCell;
    }

    public boolean getCanGoUp() {
        return canGoUp;
    }

    public void setCanGoUp(boolean canGoUp) {
        this.canGoUp = canGoUp;
    }

    public boolean checkChoice(Cell nextCellWorker) {    //ritorna un booleano a seconda del fatto che la cella nella direzione scelta dal giocatore sia "valida" o meno
        if (nextCellWorker == null || nextCellWorker.getLevel() - currentCellWorker.getLevel() > 1 || nextCellWorker.getIsOccupied()){
            return false;
        }
        else if (nextCellWorker.getLevel() - currentCellWorker.getLevel() == 1 && !canGoUp) {   //controllo per Athena
            return false;
        }
        else {
            return true;
        }
    }

    public void move(Cell nextWorkerCell) { //supponiamo di arrivare a move() con una "cella successiva" valida
        oldLevel = getCurrentWorkerCell().getLevel();
        getCurrentWorkerCell().setIsOccupied(false);
        setCurrentWorkerCell(nextWorkerCell);
        newLevel = getCurrentWorkerCell().getLevel();
        getCurrentWorkerCell().setIsOccupied(true);
    }

    public void build(Cell nextWorkerCell) {    //supponiamo di arrivare a build() con una "cella successiva" valida
        if (!nextWorkerCell.getIsOccupied()) {
            return;
        }
        else if (nextWorkerCell.getLevel() == 3) {
            nextWorkerCell.setLevel(4);
            nextWorkerCell.setIsOccupied(true);
        }
        else {
            nextWorkerCell.setLevel(nextWorkerCell.getLevel() + 1);
        }
    }

    public boolean winCondition(int oldLevel, int newLevel) {
        if (newLevel == 3 && oldLevel == 2) {
            return true;
        }
        else {
            return false;
        }
    }
}


