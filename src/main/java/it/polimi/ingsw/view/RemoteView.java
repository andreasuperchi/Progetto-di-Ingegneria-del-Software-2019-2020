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

    private class IntReceiver implements Observer<Integer> {      //Processa gli input di tipo intero
        @Override
        public void update(Integer message) {
            processIntChoice(message);
        }
    }


    @Override
    protected void showMessage(Object message) {   //Invia messaggi tramite Connection
        clientConnection.asyncSend(message);
    }

    @Override
    public void update(Model model) {    // Invia messaggi al Client in base allo stato del gioco
        String resultMessage = "";
        Model.turnPhase currentPhase = model.getCurrentPhase();
        if (model.isGameOver()) {
            if (getPlayer().equals(Model.getCurrentPlayer())) {
                resultMessage = "You Win!!!!";
            } else {
                resultMessage = "You Lose. " + Model.getCurrentPlayer().getName() + " win ";
            }
        } else {
            if (currentPhase.equals(Model.turnPhase.WORKER_PLACEMENT)) {
                resultMessage = model.getOutcome().printOutcome() + "\n\n" + Model.getMap().showInitialMap();
            } else if (currentPhase.equals(Model.turnPhase.WORKER_CHOICE) || currentPhase.equals(Model.turnPhase.MOVE) ||
                    currentPhase.equals(Model.turnPhase.BUILD) || currentPhase.equals(Model.turnPhase.SPECIAL_POWER)) {
                resultMessage = model.getOutcome().printOutcome() + "\n\n" + Model.getMap().toString();
            } else {
                resultMessage = model.getOutcome().printOutcome();
            }
        }

        showMessage(resultMessage);
    }
}
