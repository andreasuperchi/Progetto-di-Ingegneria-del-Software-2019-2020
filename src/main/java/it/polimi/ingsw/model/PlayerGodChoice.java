package it.polimi.ingsw.model;

public class PlayerGodChoice extends PlayerChoice {
    private final GodName godName;

    public PlayerGodChoice(Player player, GodName godName) {
        this.player = player;
        this.godName = godName;
    }

    public GodName getGodName() {
        return godName;
    }

}
