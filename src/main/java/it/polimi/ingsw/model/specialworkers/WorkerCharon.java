package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;

public class WorkerCharon extends Worker {

    public WorkerCharon() {
        super();
        hasSpecialPower = true;
    }

    private Cell symmetricalCell;                  //salvo cella simmetrica rispetto al mio worker
    private boolean opponentWorkerAround = false;

    @Override
    public void specialPower(Cell nextWorkerCell) {

        for (Direction d : Direction.values()) {
            try {
                //controllo che la cella sia adiacente al mio worker
                if (Model.getMap().getNextWorkerCell(getCurrentWorkerCell(), d).equals(nextWorkerCell)) {
                    opponentWorkerAround = true;
                    //controllo che ci sia un worker nemico sulla nextWorkerCell
                    if (nextWorkerCell.getThisWorker() == null || !(nextWorkerCell.getThisWorker() instanceof WorkerCharon)) {
                        //controllo che la cella data dalla simmetria, rispetto al mio worker, del worker avversario sia libera
                        try {
                            switch (d) {
                                case NORTH:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.SOUTH);
                                    break;
                                case NORTH_EAST:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.SOUTH_WEST);
                                    break;
                                case EAST:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.WEST);
                                    break;
                                case SOUTH_EAST:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.NORTH_WEST);
                                    break;
                                case SOUTH:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.NORTH);
                                    break;
                                case SOUTH_WEST:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.NORTH_EAST);
                                    break;
                                case WEST:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.EAST);
                                    break;
                                case NORTH_WEST:
                                    symmetricalCell = Model.getMap().getNextWorkerCell(this.getCurrentWorkerCell(), Direction.SOUTH_EAST);
                                    break;
                                default:
                                    throw new IllegalArgumentException();
                            }
                        } catch (ArrayIndexOutOfBoundsException e) {
                            throw new IllegalArgumentException();
                        }
                        //controllo se la symmetricalCell è occupata o no
                        if (symmetricalCell.getIsOccupied()) {
                            throw new IllegalArgumentException();
                        } else {
                            symmetricalMovement(nextWorkerCell);
                            hasUsedSpecialPower = true;
                            break;                                      //exit for
                        }
                    } else {
                        throw new IllegalArgumentException();
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                continue;
            }
        } //end for

        //controllo se la cella data non era adiacente al mio worker
        if (!opponentWorkerAround) {
            throw new IllegalArgumentException();
        }

    }


    //suppongo di chiamare il metodo con una cella 'valida' dove mettere il lavoratore avversario
    public void symmetricalMovement(Cell opponentWorkerCell) {
        Worker opponentWorker = opponentWorkerCell.getThisWorker();

        opponentWorker.getCurrentWorkerCell().setIsOccupied(false);
        opponentWorker.setCurrentWorkerCell(symmetricalCell);
        symmetricalCell.setIsOccupied(true);
    }

}
