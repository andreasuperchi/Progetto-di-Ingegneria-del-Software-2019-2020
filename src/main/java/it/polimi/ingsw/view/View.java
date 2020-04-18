package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.*;

import java.util.ArrayList;

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

    protected void processDirectionPlayerChoice(Direction input) {
        notify(new DirectionPlayerChoice(player, input));
    }

    protected void processWorkerPlayerChoice(int input) {
        notify(new WorkerPlayerChoice(player, input));
    }

    protected void processStringChoice(String input) { notify(new StringChoice(player, input));}

    @Override
    public void update(Model message) {

    }


}
