package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Direction;
import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.server.ClientConnection;
import it.polimi.ingsw.server.Connection;

public class RemoteView extends View {

    private ClientConnection clientConnection;

    public RemoteView(Player player, ClientConnection c) {
        super(player);
        this.clientConnection = c;
        c.addObserver(new IntReceiver());

    }

    private class IntReceiver implements Observer<Integer> {
        @Override
        public void update(Integer message) {
            processIntChoice(message);
        }
    }

}
