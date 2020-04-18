package it.polimi.ingsw.model;

public class DirectionPlayerChoice extends PlayerChoice {
    private Direction direction;

    public DirectionPlayerChoice(Player player, Direction diection) {
        this.player = player;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
