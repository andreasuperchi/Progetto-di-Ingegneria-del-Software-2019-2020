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
    }

    public String showInitialMap() {
        StringBuilder string = new StringBuilder(" --------  --------  --------  --------  --------  \n");
        int counter = 0;
        for (int i = 0; i < (4 * N_ROWS); i++) {
            if (i == 3 || i == 7 || i == 11 || i == 15 || i == 19) {
                string.append(" --------  --------  --------  --------  --------  \n");
            } else {
                for (int j = 0; j < N_COLS; j++) {
                    for (int r = 0; r < 10; r++) {
                        switch (r) {
                            case 1:
                            case 2:
                            case 3:
                            case 6:
                            case 7:
                            case 8:
                                string.append(" ");
                                break;
                            case 4:
                                if ((i - 1) % 4 == 0) {
                                    if (grid[i / 4][j].getIsOccupied()) {
                                        string.append(grid[i / 4][j].toString());
                                    } else {
                                        string.append(counter);
                                    }
                                    counter++;
                                } else {
                                    string.append(" ");
                                }
                                break;
                            case 5:
                                if (!((i - 1) % 4 == 0 && counter > 9)) {
                                    string.append(" ");
                                }
                                break;
                            default:
                                string.append("|");
                        }
                    }
                }

                string.append("\n");
            }
        }
        return string.toString();
    }


    @Override
    public String toString() {
        StringBuilder string = new StringBuilder(" --------  --------  --------  --------  --------  \n");
        int counter;
        for (int i = 0; i < (4 * N_ROWS); i++) {
            if ((i - 3) % 4 == 0) {
                string.append(" --------  --------  --------  --------  --------  \n");
            } else {
                for (int j = 0; j < N_COLS; j++) {
                    for (int r = 0; r < 10; r++) {
                        counter = i % 4;
                        switch (r) {
                            case 1:
                                if (counter == 0) {
                                    string.append(grid[i / 4][j].getLevel());
                                } else {
                                    string.append(" ");
                                }
                                break;
                            case 2:
                            case 3:
                            case 5:
                            case 6:
                            case 7:
                            case 8:
                                string.append(" ");
                                break;
                            case 4:
                                if ((i - 1) % 4 == 0) {
                                    string.append(grid[i / 4][j].toString());
                                    if (grid[i / 4][j].getIsOccupied()) {
                                        r++;
                                    }
                                } else {
                                    string.append(" ");
                                }
                                break;
                            default:
                                string.append("|");
                        }
                    }
                }

                string.append("\n");
            }
        }
        return string.toString();
    }
}
