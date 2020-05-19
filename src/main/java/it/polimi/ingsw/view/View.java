package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.*;

public abstract class View extends Observable<Choice> implements Observer<Model> {
    private Player player;

    protected View(Player player) {
        this.player = player;
    }

    protected Player getPlayer() {
        return player;
    }

    protected void processIntChoice(int input) {
        notify(new IntChoice(player, input));
    }

    protected abstract void showMessage(Object message);

}
