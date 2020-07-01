package it.polimi.ingsw.model;

public enum Direction {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    /**
     * parses the direction received to the corresponding enum
     *
     * @param input is the direction received
     * @return the enum value corresponding to the String received
     */
    public static Direction parseInput(String input) {
        return Enum.valueOf(Direction.class, input.toUpperCase());
    }
}


