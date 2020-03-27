package it.polimi.ingsw.model;

public class Worker {
    private Player player;
    private Cell currentWorkerCell;
    private boolean canGoUp;
    private int oldLevel;
    private int newLevel;

    public Worker(Player player, Cell currentWorkerCell) {
        this.player = player;
        this.currentWorkerCell = currentWorkerCell;
    }

    public Player getPlayerName() {
        return player;
    }

    public void setPlayerName(Player player) {
        this.player = player;
    }

    public Cell getCurrentWorkerCell() {
        return currentWorkerCell;
    }

    public void setCurrentWorkerCell(Cell currentWorkerCell) {
        this.currentWorkerCell = currentWorkerCell;
    }

    public boolean getCanGoUp() {
        return canGoUp;
    }

    public void setCanGoUp(boolean canGoUp) {
        this.canGoUp = canGoUp;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(int oldLevel) {
        this.oldLevel = oldLevel;
    }

    public int getNewLevel() {
        return newLevel;
    }

    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }

    public boolean checkChoice(Cell nextWorkerCell) {    //ritorna un booleano a seconda del fatto che la cella nella direzione scelta dal giocatore sia "valida" o meno
        if (nextWorkerCell == null || nextWorkerCell.getLevel() - currentWorkerCell.getLevel() > 1 || nextWorkerCell.getIsOccupied()){
            return false;
        }
        else if (nextWorkerCell.getLevel() - currentWorkerCell.getLevel() == 1 && !canGoUp) {   //controllo per Athena
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
        if (nextWorkerCell.getIsOccupied() || this.getCurrentWorkerCell().equals(nextWorkerCell)) {
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

    public boolean winCondition() {
        if (newLevel == 3 && oldLevel == 2) {
            return true;
        }
        else {
            return false;
        }
    }
}


