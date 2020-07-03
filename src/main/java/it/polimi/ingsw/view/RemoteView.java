package it.polimi.ingsw.view;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.server.ClientConnection;
import it.polimi.ingsw.server.Connection;

public class RemoteView extends View {

    private ClientConnection clientConnection;

    public RemoteView(Player player, ClientConnection c) {
        super(player);
        this.clientConnection = c;
        c.addObserver(new IntReceiver());

    }


    /**
     * Processes an integer type input
     */
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
            if (!getPlayer().getIsInGame() || !getPlayer().equals(model.getCurrentPlayer())) {
                resultMessage = "You Lose!";
            }else {
                resultMessage = "You Win!";
            }
        } else {
            if (!getPlayer().getIsInGame()) {
                resultMessage = "close";
            } else if (Model.getCurrentPlayer().equals(getPlayer())) {
                if (currentPhase.equals(Model.turnPhase.WORKER_PLACEMENT)) {
                    resultMessage = Model.getMap().showInitialMap() + "\n\n" + model.getOutcome().printOutcome();
                } else if (currentPhase.equals(Model.turnPhase.WORKER_CHOICE) || currentPhase.equals(Model.turnPhase.ACTION_CHOICE) ||
                        currentPhase.equals(Model.turnPhase.MOVE) || currentPhase.equals(Model.turnPhase.BUILD) ||
                        currentPhase.equals(Model.turnPhase.SPECIAL_POWER)) {
                    resultMessage = Model.getMap().toString() + model.getOutcome().printOutcome();
                } else {
                    resultMessage = model.getOutcome().printOutcome();
                }
            } else {
                resultMessage = "\u001b[33;1mWaiting for the other players to make their moves...\u001b[0m";
            }
        }

        showMessage("\n\n\n" + resultMessage);
    }
}
