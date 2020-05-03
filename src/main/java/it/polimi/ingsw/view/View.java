package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.*;

public abstract class View extends Observable<PlayerChoice> implements Observer<Model> {
    private Player player;

    protected View(Player player) {
        this.player = player;
    }

    protected Player getPlayer() {
        return player;
    }

    /*protected void processGodChoice(GodName input) {
        notify(new GodChoice(player, input));
    }

    protected void processNumberOfPlayersChoice(int input) {
        notify(new NumberOfPlayersChoice(player, input));
    }

    protected void processDirectionPlayerChoice(Direction input) {
        notify(new DirectionChoice(player, input));
    }

    protected void processWorkerPlayerChoice(int input) {
        notify(new WorkerChoice(player, input));
    }

    protected void processStringChoice(String input) { notify(new StringChoice(player, input));}
*/

    protected void processIntChoice(int input) {
        notify(new IntChoice(player, input));
    }


    @Override
    public void update(Model message) {

    }


}
