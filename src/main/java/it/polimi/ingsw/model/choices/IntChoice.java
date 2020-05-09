package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Player;

public class IntChoice extends Choice {
    private final int value;

    public IntChoice(Player player, int value) {
        this.player = player;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
