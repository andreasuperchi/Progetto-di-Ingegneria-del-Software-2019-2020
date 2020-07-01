package it.polimi.ingsw.model.specialworkers;

        import it.polimi.ingsw.model.*;

public class WorkerHestia extends Worker {

    public WorkerHestia() {
        super();
        this.hasSpecialPower = true;
    }

    /**
     * Use the special power that consist in additional move actions if nextWorkerCell
     * isn't a perimetrical cell
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (isPerimetricCell(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            build(nextWorkerCell);
            hasUsedSpecialPower = true;
        }
    }

    /**
     * Check if the worker can use his special power
     *
     * @return
     */
    @Override
    public boolean canUseSpecialPower() {
        if (!hasMoved || !hasBuilt) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if the cell is perimetrical
     *
     * @param cell
     * @return
     */
    private boolean isPerimetricCell(Cell cell) {
        if (cell.getRowNumber() == 0 || cell.getRowNumber() == 4) {
            return true;
        } else if (cell.getColumnNumber() == 0 || cell.getColumnNumber() == 4) {
            return true;
        } else {
            return false;
        }

    }
}


