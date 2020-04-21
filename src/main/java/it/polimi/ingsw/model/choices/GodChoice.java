package it.polimi.ingsw.model.choices;

import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.choices.PlayerChoice;

public class GodChoice extends PlayerChoice {
    private final GodName godName;

    public GodChoice(Player player, GodName godName) {
        this.player = player;
        this.godName = godName;
    }

    public GodName getGodName() {
        return godName;
    }

}
