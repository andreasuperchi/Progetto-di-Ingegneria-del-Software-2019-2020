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

    /**
     * Builds a new Worker and initializes his attributes.
     * When a worker is created, he is not assigned to a Cell yet.
     */
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

    /**
     * Set the Worker's position to a specific Cell.
     * The Cell will be set to occupied, meaning that no other Worker can be set to this Cell.
     *
     * @param currentWorkerCell is the Cell where the Worker is going to be placed
     */
    public void setCurrentWorkerCell(Cell currentWorkerCell) {
        if (currentWorkerCell.getIsOccupied()) {
            throw new IllegalArgumentException();
        } else {
            currentWorkerCell.setIsOccupied(true);
            currentWorkerCell.setThisWorker(this);
            this.currentWorkerCell = currentWorkerCell;
        }
    }

    public boolean getHasUsedSpecialPower() {
        return hasUsedSpecialPower;
    }

    public void setHasUsedSpecialPower(boolean hasUsedSpecialPower) {
        this.hasUsedSpecialPower = hasUsedSpecialPower;
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

    public void setCanBeUsed(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }

    /**
     * Checks if a Worker can move to a specific Cell
     *
     * @param nextWorkerCell is the Cell where the Worker wants to move
     * @return a boolean that is true is the move action is "legal", false otherwise
     */
    public boolean checkMove(Cell nextWorkerCell) {
        int levelDiff = nextWorkerCell.getLevel() - this.getCurrentWorkerCell().getLevel();

        return !(this.getCurrentWorkerCell().equals(nextWorkerCell)) &&
                ((!nextWorkerCell.getIsOccupied() && levelDiff <= 0) ||
                        (!nextWorkerCell.getIsOccupied() && levelDiff == 1 && canGoUp));
    }

    /**
     * Checks if a Worker can build in a specific Cell
     *
     * @param nextWorkerCell is the Cell where the Worker wants to build
     * @return a boolean that is true if the build is "legal", false otherwise
     */
    public boolean checkBuild(Cell nextWorkerCell) {
        return !nextWorkerCell.getIsOccupied() && !(nextWorkerCell.equals(this.currentWorkerCell));
    }

    /**
     * Tells if this worker can use his special power. Standard worker don't have special powers
     *
     * @return always false, because standard workers(workers that are not special) don't have special powers
     */
    public boolean canUseSpecialPower() {
        return false;
    }

    /**
     * Checks the Cells that surrounds the Worker
     *
     * @return a boolean that is true if at least one cell is feasible for an action, false otherwise
     */
    public boolean checkSurroundingCells() {
        for (Direction d : Direction.values()) {
            try {
                Cell cell = Model.getMap().getNextWorkerCell(getCurrentWorkerCell(), d);
                if (checkMove(cell)) {
                    return true;
                }
            } catch (ArrayIndexOutOfBoundsException ignored) {
            }
        }
        return false;
    }

    /**
     * Moves the Worker from his current Cell to the specified Cell. Stores the values of the levels of the cells
     * in 2 different attributes. Also sets the hasMoved flag, which represents the fact that the Worker has
     * built in this turn.
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be moved
     */
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

    /**
     * Builds a new level on the specified Cell. If the new level is 4, the Cell is
     * permanently set to occupied, meaning that no one can move to that Cell.
     * Also sets the hasBuilt flag, which represents the fact that the Worker has built in this turn
     *
     * @param nextWorkerCell is the Cell where the Worker is going to be build
     */
    public void build(Cell nextWorkerCell) {
        if (!checkBuild(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            if (nextWorkerCell.getLevel() == 3) {
                nextWorkerCell.setLevel(4);
                nextWorkerCell.setIsOccupied(true);
            } else {
                nextWorkerCell.setLevel(nextWorkerCell.getLevel() + 1);
            }
            this.hasBuilt = true;
        }
    }

    /**
     * It's an empty method that the standard Workers "don't have". Specifies the additional
     * power that some Gods have.
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
    public void specialPower(Cell nextWorkerCell) {
    }

    /**
     * Checks if the Worker has satisfied the standard win condition
     *
     * @return true if the worker has gone up from a level 2 cell to a lever 3 cell, false otherwise
     */
    public boolean winCondition() {
        return newLevel == 3 && oldLevel == 2;
    }

}


