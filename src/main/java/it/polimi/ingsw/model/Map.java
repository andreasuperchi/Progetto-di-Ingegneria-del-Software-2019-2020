package it.polimi.ingsw.model;

public class Map {
    private static final int N_ROWS = 5;
    private static final int N_COLS = 5;

    private final Cell[][] grid = new Cell[Map.N_COLS][Map.N_ROWS];
    private int completedTowers;

    /**
     * Builds a new 5x5 board, initializing every cell by calling the cell constructor. Also sets the
     * initial number of completed towers to 0
     */
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

    public int getCompletedTowers() {
        return completedTowers;
    }

    public void setCompletedTowers(int completedTowers) {
        this.completedTowers = completedTowers;
    }

    /**
     * Gets the cell on the board starting from the baseCell and moving towards the direction specified
     *
     * @param baseCell  is the cell where the worker is actually starting his action
     * @param direction is the direction where the worker wants to make his action
     * @return the next cell starting from the baseCell and moving towards the direction
     */
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

    /**
     * Shows a starting board, in which each cell is associated with a number. It's used
     * for the placement of the workers at the beginning of the game
     *
     * @return the board as a String, showing for each cell the number that is associated with and the worker symbol if the cell is occupied
     */
    public String showInitialMap() {
        StringBuilder string = new StringBuilder(" --------  --------  --------  --------  --------  \n");
        int counter = 1;
        for (int i = 0; i < (4 * N_ROWS); i++) {
            if ((i - 3) % 4 == 0) {
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
                                        r++;
                                    } else {
                                        string.append(counter);
                                    }
                                    counter++;
                                } else {
                                    string.append(" ");
                                }
                                break;
                            case 5:
                                if (!((i - 1) % 4 == 0 && counter > 10)) {
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

    /**
     * Is the method used for printing the board during the game. For each cell, shows the level and, if occupied, the worker symbol
     * corresponding to the worker that is occupying that cell
     *
     * @return the board represented as a String
     */
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
                                    if (grid[i / 4][j].getLevel() == 4) {
                                        string.append("\u001b[31;1m").append(grid[i / 4][j].getLevel()).append("\u001b[0m");
                                    } else {
                                        string.append(grid[i / 4][j].getLevel());
                                    }
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
