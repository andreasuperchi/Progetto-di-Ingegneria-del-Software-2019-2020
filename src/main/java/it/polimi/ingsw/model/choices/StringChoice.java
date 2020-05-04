package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.Player;

public class StringChoice extends Choice {
    private String string;

    public StringChoice(Player player, String string) {
        this.player = player;
        this.string = string;
    }

    public String getString() {
        return string;
    }
}
