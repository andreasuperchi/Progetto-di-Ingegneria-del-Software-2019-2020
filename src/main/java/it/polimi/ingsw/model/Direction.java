package it.polimi.ingsw.model;

public enum Direction  {
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;

    public static Direction parseInput(String input) {
        return Enum.valueOf(Direction.class, input.toUpperCase());
    }

}


