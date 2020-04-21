package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Direction;
import it.polimi.ingsw.model.Player;

public class DirectionChoice extends PlayerChoice {
    private final Direction direction;

    public DirectionChoice(Player player, Direction direction) {
        this.player = player;
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
