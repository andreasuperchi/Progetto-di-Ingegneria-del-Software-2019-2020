package it.polimi.ingsw.model;

public class Worker {
    private Player player;
    private Cell currentWorkerCell;
    private static boolean canGoUp;
    private int oldLevel;
    private int newLevel;
    private boolean hasMoved;
    private boolean hasBuilt;
    private boolean canBeUsed;

    public Worker(Player player) {
        this.player = player;
        canGoUp = true;
        this.hasMoved = false;
        this.hasBuilt = false;
        this.canBeUsed = true;
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
        currentWorkerCell.setIsOccupied(true);
        currentWorkerCell.setThisWorker(this);
        this.currentWorkerCell = currentWorkerCell;
    }

    public boolean getCanGoUp() {
        return canGoUp;
    }

    public void setCanGoUp(boolean canGoUp) {
        Worker.canGoUp = canGoUp;
    }

    public int getOldLevel() {
        return oldLevel;
    }

    public void setOldLevel(int oldLevel) {
        this.oldLevel = oldLevel;
    }

    public int getNewLevel() { return newLevel; }

    public void setNewLevel(int newLevel) {
        this.newLevel = newLevel;
    }

    public boolean getHasMoved() { return hasMoved; }

    public void setHasMoved(boolean hasMoved) { this.hasMoved = hasMoved; }

    public boolean getHasBuilt() { return hasBuilt; }

    public void setHasBuilt(boolean hasBuilt) { this.hasBuilt = hasBuilt; }

    public boolean getCanBeUsed() { return canBeUsed; }

    public void setCanBeUsed(boolean canBeUsed) { this.canBeUsed = canBeUsed; }

    public boolean checkMove(Cell nextWorkerCell) {    //ritorna un booleano a seconda del fatto che la cella nella direzione scelta dal giocatore sia "valida" o meno
        if (nextWorkerCell == null || nextWorkerCell.getLevel() - currentWorkerCell.getLevel() > 1 || nextWorkerCell.getIsOccupied()) {
            return false;
        } else if (nextWorkerCell.getLevel() - currentWorkerCell.getLevel() == 1 && !canGoUp) {   //controllo per Athena
            return false;
        } else {
            return true;
        }
    }

    public boolean checkBuild(Cell nextWorkerCell) {
        if (nextWorkerCell.getIsOccupied() || this.getCurrentWorkerCell().equals(nextWorkerCell)) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkSurroundingCells(Map map) {
        for (Direction d : Direction.values()){
            if (checkMove(map.getNextWorkerCell(getCurrentWorkerCell(), d))) {
                return true;
            }
        }
        return false;
    }

    public void move(Cell nextWorkerCell) { //supponiamo di arrivare a move() con una "cella successiva" valida
        oldLevel = getCurrentWorkerCell().getLevel();
        getCurrentWorkerCell().setIsOccupied(false);
        setCurrentWorkerCell(nextWorkerCell);
        newLevel = getCurrentWorkerCell().getLevel();
        getCurrentWorkerCell().setIsOccupied(true);
        // nextWorkerCell.setThisWorker(this);
    }

    public void build(Cell nextWorkerCell) {    //supponiamo di arrivare a build() con una "cella successiva" valida
        if (nextWorkerCell.getLevel() == 3) {
            nextWorkerCell.setLevel(4);
            nextWorkerCell.setIsOccupied(true);
        } else {
            nextWorkerCell.setLevel(nextWorkerCell.getLevel() + 1);
        }
    }

    public boolean winCondition() {
        if (newLevel == 3 && oldLevel == 2) {
            return true;
        } else {
            return false;
        }
    }

    public void turn(Cell nextWorkerCell) {
        if (!this.hasMoved) {
            this.move(nextWorkerCell);
            this.hasMoved = true;
        } else if (!this.hasBuilt) {
            this.build(nextWorkerCell);
            this.hasBuilt = true;
        }
    }

    public Outcome setMoveCompleted() {
        this.hasMoved = true;
        return Outcome.MOVE_COMPLETED;
    }

    public Outcome setBuildCompleted() {
        this.hasBuilt = true;
        return Outcome.BUILD_COMPLETED;
    }
}


