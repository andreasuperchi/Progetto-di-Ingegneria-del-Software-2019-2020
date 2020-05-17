package it.polimi.ingsw.model;

public class Worker {
    protected String symbol;
    protected Cell currentWorkerCell;
    protected static boolean canGoUp;
    protected boolean hasSpecialPower;
    protected int oldLevel;
    protected int newLevel;
    protected boolean hasMoved;
    protected boolean hasBuilt;
    protected boolean hasUsedSpecialPower;
    protected boolean canBeUsed;

    public Worker() {
        canGoUp = true;
        this.hasMoved = false;
        this.hasBuilt = false;
        this.canBeUsed = true;
        this.currentWorkerCell = null;
    }

    public Cell getCurrentWorkerCell() {
        return currentWorkerCell;
    }

    public void setCurrentWorkerCell(Cell currentWorkerCell) {
        if (currentWorkerCell.getIsOccupied()) {
            throw new IllegalArgumentException();
        } else {
            currentWorkerCell.setIsOccupied(true);
            currentWorkerCell.setThisWorker(this);
            this.currentWorkerCell = currentWorkerCell;
        }
    }

    public String getSymbol() {
        return symbol;
    }

    public boolean getHasUsedSpecialPower() {
        return hasUsedSpecialPower;
    }

    public void setHasUsedSpecialPower(boolean hasUsedSpecialPower) {
        this.hasUsedSpecialPower = hasUsedSpecialPower;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public static boolean getCanGoUp() {
        return canGoUp;
    }

    public static void setCanGoUp(boolean canGoUp) {
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

    public void setHasMoved(boolean hasMoved) {
        this.hasMoved = hasMoved;
    }

    public boolean getHasBuilt() {
        return hasBuilt;
    }

    public void setHasBuilt(boolean hasBuilt) {
        this.hasBuilt = hasBuilt;
    }

    public boolean getCanBeUsed() {
        return canBeUsed;
    }

    public void setCanBeUsed(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

//    public boolean checkMove(Cell nextWorkerCell) {    //arrivo qui con una cella che esiste
//        if (nextWorkerCell.getLevel() - currentWorkerCell.getLevel() > 1 || nextWorkerCell.getIsOccupied()) {
//            return false;
//        } else if (nextWorkerCell.getLevel() - currentWorkerCell.getLevel() == 1 && !canGoUp) {   //controllo per Athena
//            return false;
//        } else {
//            return true;
//        }
//    }

    public boolean checkMove(Cell nextWorkerCell) {
        int levelDiff = nextWorkerCell.getLevel() - this.getCurrentWorkerCell().getLevel();

        return !(this.getCurrentWorkerCell().equals(nextWorkerCell)) &&
                ((!nextWorkerCell.getIsOccupied() && levelDiff <= 0) ||
                        (!nextWorkerCell.getIsOccupied() && levelDiff == 1 && canGoUp));
    }

    public boolean checkBuild(Cell nextWorkerCell) {
        return !nextWorkerCell.getIsOccupied();
    }

    public boolean checkSurroundingCells() {
        for (Direction d : Direction.values()) {
            if (checkMove(Model.getMap().getNextWorkerCell(getCurrentWorkerCell(), d))) {
                return true;
            }
        }
        return false;
    }

    public void move(Cell nextWorkerCell) {
        if (!checkMove(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            oldLevel = getCurrentWorkerCell().getLevel();
            getCurrentWorkerCell().setIsOccupied(false);
            getCurrentWorkerCell().setThisWorker(null);
            setCurrentWorkerCell(nextWorkerCell);
            newLevel = getCurrentWorkerCell().getLevel();
            getCurrentWorkerCell().setIsOccupied(true);
            this.hasMoved = true;
        }
    }

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
        }
    }

    //ogni lavoratore farà l'Override
    public void specialPower(Cell nextWorkerCell) {

    }

    public boolean winCondition() {
        if (newLevel == 3 && oldLevel == 2) {
            return true;
        } else {
            return false;
        }
    }

}


