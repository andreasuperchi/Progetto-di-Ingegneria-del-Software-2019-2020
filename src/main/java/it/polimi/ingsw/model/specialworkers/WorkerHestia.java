package it.polimi.ingsw.model.specialworkers;

        import it.polimi.ingsw.model.*;

public class WorkerHestia extends Worker {

    /**
     * builds a new worker using his super-class constructor and sets to sets to true
     * the special power availability
     */
    public WorkerHestia() {
        super();
        this.hasSpecialPower = true;
    }

    /**
     * Uses the special power that consist in additional move actions if nextWorkerCell
     * isn't a cell on the perimeter
     *
     * @param nextWorkerCell is the Cell where the Worker wants to use his special power
     */
    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (isPerimeterCell(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            build(nextWorkerCell);
            hasUsedSpecialPower = true;
        }
    }

    /**
     * Check if the worker can use his special power
     *
     * @return true if the worker has both built and moved
     */
    @Override
    public boolean canUseSpecialPower() {
        return hasMoved && hasBuilt;
    }

    /**
     * Check if the cell is on the perimeter
     *
     * @param cell is the specified cell that is going to be checked
     * @return true if the cell is on the perimeter
     */
    private boolean isPerimeterCell(Cell cell) {
        if (cell.getRowNumber() == 0 || cell.getRowNumber() == 4) {
            return true;
        } else return cell.getColumnNumber() == 0 || cell.getColumnNumber() == 4;

    }
}


