package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Direction;
import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;

public class RemoteView extends View {

    protected RemoteView(Player player) {
        super(player);
    }

    private class IntReceiver implements Observer<Integer> {
        @Override
        public void update(Integer message) {
            processIntChoice(message);
        }
    }


}
