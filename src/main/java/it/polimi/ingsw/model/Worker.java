package it.polimi.ingsw.model;

public class Worker {
    //private Player player;
    protected Cell currentWorkerCell;
    protected static boolean canGoUp;
    protected int oldLevel;
    protected int newLevel;
    protected boolean hasMoved;
    protected boolean hasBuilt;
    protected boolean canBeUsed;

    public Worker() {
        // this.player = player;
        canGoUp = true;
        this.hasMoved = false;
        this.hasBuilt = false;
        this.canBeUsed = true;
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

    public boolean checkSurroundingCells() {
        for (Direction d : Direction.values()){
            if (checkMove(Model.getMap().getNextWorkerCell(getCurrentWorkerCell(), d))) {
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

    public Outcome setMoveCompleted() {
        this.hasMoved = true;
        return Outcome.MOVE_COMPLETED;
    }

    public Outcome setBuildCompleted() {
        this.hasBuilt = true;
        return Outcome.BUILD_COMPLETED;
    }
}


