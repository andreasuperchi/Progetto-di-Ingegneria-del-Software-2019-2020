package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.*;
import it.polimi.ingsw.view.Observer;

public class Controller implements Observer<PlayerChoice> {
    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void update(PlayerChoice message) {
        if (message instanceof NumberOfPlayersChoice) {
            model.setNumberOfPlayers((NumberOfPlayersChoice) message);
        }
        else if (message instanceof GodChoice) {
            //per aggiungere gli dèi all'array setAvailableGods
            if (model.getAvailableGods().size() < model.getNumberOfPlayers()) {
                model.setAvailableGods((GodChoice) message);
            }
            //oppure per la scelta del dio di un giocatore
            else {
                model.setPlayerGod((GodChoice) message);
            }
        } else if (message instanceof WorkerChoice) {
            model.setWorkerChoice((WorkerChoice) message);
        } else if (message instanceof DirectionChoice) {
            if (model.getCurrentWorker().getHasMoved()) {
                model.setDirectionBuild((DirectionChoice) message);
            } else {
                model.setDirectionMove((DirectionChoice) message);
            }
        } else if (message instanceof StringChoice) {
            if (model.getCurrentWorker().getHasMoved() && !model.getCurrentWorker().getHasBuilt()) {
                model.setCurrentWorkerHasMoved((StringChoice) message);
            } else if (model.getCurrentWorker().getHasBuilt()) {
                model.setCurrentWorkerHasBuild((StringChoice) message);
            }
        } else if (model.getCurrentPlayer().isEndOfTurn()) {
            model.endTurn();
        }
    }
}
