package it.polimi.ingsw.model.specialworkers;

        import it.polimi.ingsw.model.*;

public class WorkerHestia extends Worker {

    public WorkerHestia() {
        super();
        this.hasSpecialPower = true;
    }

    @Override
    public void specialPower(Cell nextWorkerCell) {
        if (isPerimetricCell(nextWorkerCell)) {
            throw new IllegalArgumentException();
        } else {
            build(nextWorkerCell);
            hasUsedSpecialPower = true;
        }
    }

    @Override
    public boolean canUseSpecialPower() {
        if (!hasMoved || !hasBuilt) {
            return false;
        } else {
            return true;
        }
    }

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


