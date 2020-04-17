package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;

public abstract class View extends Observable<PlayerChoice> implements Observer<Model> {
    private Player player;

    protected View(Player player) {
        this.player = player;
    }

    protected Player getPlayer() {
        return player;
    }

    protected void processGodChoice(GodName input) {
        notify(new PlayerGodChoice(player, input));
    }

    protected void processNumberOfPlayersChoice(int input) {
        notify(new NumberOfPlayersChoice(player, input));
    }


    @Override
    public void update(Model message) {

    }


}
