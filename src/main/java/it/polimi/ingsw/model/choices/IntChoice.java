package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Player;

public class IntChoice extends Choice {
    private final int value;

    /**
     * builds and intChoice that associates an integer value with the player
     * that typed in the value
     *
     * @param player is the player that made the action
     * @param value  is the value typed in by the player (in the client side)
     */
    public IntChoice(Player player, int value) {
        this.player = player;
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
