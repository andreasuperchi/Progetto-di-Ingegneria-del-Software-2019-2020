package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Player;

public class IntChoice extends PlayerChoice {
    private final int value;

    public IntChoice(Player player, int numberOfPlayers) {
        this.player = player;
        this.value = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return value;
    }
}
