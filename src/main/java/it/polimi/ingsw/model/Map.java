package it.polimi.ingsw.model;

public class Map {
    private static final int N_ROWS = 5;
    private static final int N_COLS = 5;

    protected Cell[][] grid = new Cell[Map.N_COLS][Map.N_ROWS];
    private int completedTowers;

    public Map() {
        for (int row = 0; row < N_ROWS; row++) {
            for (int column = 0; column < N_COLS; column++) {
                grid[row][column] = new Cell(row, column);
            }
        }
        completedTowers = 0;
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public int getCompletedTowers() {
        return completedTowers;
    }

    public void setCompletedTowers(int completedTowers) {
        this.completedTowers = completedTowers;
    }

    //ritorna la cella nella direzione scelta rispetto alla "cella base", dove "cella base" è la cella in cui si trova il lavoratore prima di compiere il movimento
    public Cell getNextWorkerCell(Cell baseCell, Direction direction) {
        try {
            switch (direction) {
                case NORTH:
                    return grid[baseCell.getRowNumber() - 1][baseCell.getColumnNumber()];
                case SOUTH:
                    return grid[baseCell.getRowNumber() + 1][baseCell.getColumnNumber()];
                case EAST:
                    return grid[baseCell.getRowNumber()][baseCell.getColumnNumber() + 1];
                case WEST:
                    return grid[baseCell.getRowNumber()][baseCell.getColumnNumber() - 1];
                case NORTH_EAST:
                    return grid[baseCell.getRowNumber() - 1][baseCell.getColumnNumber() + 1];
                case NORTH_WEST:
                    return grid[baseCell.getRowNumber() - 1][baseCell.getColumnNumber() - 1];
                case SOUTH_EAST:
                    return grid[baseCell.getRowNumber() + 1][baseCell.getColumnNumber() + 1];
                case SOUTH_WEST:
                    return grid[baseCell.getRowNumber() + 1][baseCell.getColumnNumber() - 1];
                default:
                    throw new IllegalArgumentException("Unexpected case!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }

    public void showMap() {                         //TODO Cambaire output a String
        Cell cell;
        int[] levels = new int[5];                 //i livelli delle celle di una riga
        Worker[] workers = new Worker[5];         // i worker che sono presenti sulla riga

        //i le righe, j le colonne
        for (int i = 0; i < 5; i++) {
            System.out.println();               //vado nella nuova riga
            System.out.print('|');
            for (int j = 0; j < 5; j++) {
                cell = grid[i][j];
                System.out.print("---");
                System.out.print('|');
                switch (cell.getLevel()) {
                    case 0:
                        levels[j] = 0;
                        break;
                    case 1:
                        levels[j] = 1;
                        break;
                    case 2:
                        levels[j] = 2;
                        break;
                    case 3:
                        levels[j] = 3;
                        break;
                    case 4:
                        levels[j] = 4;
                        break;
                    default:
                        levels[j] = 0;
                        break;
                }
                //recupero il worker se è sulla cella
                workers[j] = cell.getThisWorker();
            }

            //scrivo i contenuti delle celle
            System.out.println();
            for (int j = 0; j < 6; j++) {
                System.out.print('|');
                System.out.print(levels[j]);
                if (!workers[j].equals(null)) {
                    System.out.print(workers[j].getSymbol());

                } else {
                    System.out.print("   ");
                }
            }
        }
    }

}
