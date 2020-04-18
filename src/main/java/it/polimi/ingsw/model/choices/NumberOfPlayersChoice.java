package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Player;

public class NumberOfPlayersChoice extends PlayerChoice {
    private final int numberOfPlayers;

    public NumberOfPlayersChoice(Player player, int numberOfPlayers) {
        this.player = player;
        this.numberOfPlayers = numberOfPlayers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }
}
