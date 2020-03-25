package it.polimi.ingsw.model;

public class Map {
    private static final int N_ROWS = 5;
    private static final int N_COLS = 5;

    private Cell[][] map = new Cell[Map.N_COLS][Map.N_ROWS];
    private int completedTowers;

    public Map() {
        for (int row = 0; row <= 4; row++) {
            for (int column = 0; column <= 4; column++) {
                map[row][column] = new Cell(row, column);
            }
        }
        completedTowers = 0;
    }

    //ritorna la cella nella direzione scelta rispetto alla "cella base", dove "cella base" è la cella in cui si trova il lavoratore prima di compiere il movimento
    public Cell getNextCellWorker(Cell baseCell, Direction direction) {
        try {
            switch (direction) {
                case NORTH:
                    return map[baseCell.getRowNumber() - 1][baseCell.getColumnNumber()];
                case SOUTH:
                    return map[baseCell.getRowNumber() + 1][baseCell.getColumnNumber()];
                case EAST:
                    return map[baseCell.getRowNumber()][baseCell.getColumnNumber() - 1];
                case WEST:
                    return map[baseCell.getRowNumber()][baseCell.getColumnNumber() + 1];
                case NORTH_EAST:
                    return map[baseCell.getRowNumber() - 1][baseCell.getColumnNumber() + 1];
                case NORTH_WEST:
                    return map[baseCell.getRowNumber() - 1][baseCell.getColumnNumber() - 1];
                case SOUTH_EAST:
                    return map[baseCell.getRowNumber() + 1][baseCell.getColumnNumber() + 1];
                case SOUTH_WEST:
                    return map[baseCell.getRowNumber() + 1][baseCell.getColumnNumber() - 1];
                default:
                    throw new RuntimeException("Unexpected case!");
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
