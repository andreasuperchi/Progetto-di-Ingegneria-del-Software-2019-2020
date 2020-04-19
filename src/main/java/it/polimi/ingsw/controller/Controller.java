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
        else if (message instanceof PlayerGodChoice) {
            //per aggiungere gli dèi all'array setAvailableGods
            if (model.getAvailableGods().size() < model.getNumberOfPlayers()) {
                model.setAvailableGods((PlayerGodChoice) message);
            }
            //oppure per la scelta del dio di un giocatore
            else {
                model.setPlayerGod((PlayerGodChoice) message);
            }
        } else if (message instanceof WorkerPlayerChoice) {
            model.setCurrentWorker((WorkerPlayerChoice) message);
        } else if (message instanceof DirectionPlayerChoice) {
            if (model.getCurrentWorker().getHasMoved()) {
                model.setDirectionBuild((DirectionPlayerChoice) message);
            } else {
                model.setDirectionMove((DirectionPlayerChoice) message);
            }
        } else if (message instanceof StringChoice) {
            if (model.getCurrentWorker().getHasMoved() && !model.getCurrentWorker().getHasBuilt()) {
                model.setCurrentWorkerHasMoved((StringChoice) message);
            } else if (model.getCurrentWorker().getHasBuilt()) {
                model.setCurrentWorkerHasBuild((StringChoice) message);
            }
        } else if (model.getCurrentWorker().isEndOfTurn()) {

        }
    }
}
