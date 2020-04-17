package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.Observer;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;
import java.util.Observable;

public class Controller implements Observer<PlayerChoice> {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void chooseWorker(int workerIndex) {
        model.setCurrentWorker(model.getCurrentPlayer().getWorkers()[workerIndex]);
    }

    public void game(Cell nextCell) {
        model.getCurrentWorker().turn(nextCell);
        model.setGameOver(model.getCurrentWorker().winCondition());
        if (model.getGameOver()) {
            notify();   //TODO: controllare qui
        }
        for (Player player : model.getPlayers()) {
            for (Worker worker : player.getWorkers()) {
                worker.setCanBeUsed(worker.checkSurroundingCells(Model.getMap()));
            }
        }
        if (model.getCurrentWorker().getHasMoved() && model.getCurrentWorker().getHasBuilt()) {
            endTurn();
        }
    }

    public void endTurn() {
        if (model.getCurrentPlayer().equals(model.getPlayers().get(model.getPlayers().size()))) {
            model.setCurrentPlayer(model.getPlayers().get(0));
        } else {
            model.setCurrentPlayer(model.getPlayers().get(model.getPlayers().indexOf(model.getCurrentPlayer()) + 1));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o != view) {
            throw new IllegalArgumentException();
        }
        if (arg instanceof Integer) {   //mi arriva il numero di giocatori
            model.setNumberOfPlayers((Integer) arg);
        } else if (arg instanceof Player) {   //mi arriva un giocatore
            model.addPlayer((Player) arg);
        } else if (arg instanceof ArrayList) {    //mi arriva l'array degli dèi scelti
            model.setAvailableGods((ArrayList<GodName>) arg);
        } else if (arg instanceof GodName) {  //mi arriva il "nome di un dio"
            model.setPlayerWorker((GodName) arg);
            endTurn();
        } else if (arg instanceof Cell[]) {
            model.setWorkerStartPosition((Cell[]) arg);
        } else {
            game((Cell) arg);
        }

    }
}
