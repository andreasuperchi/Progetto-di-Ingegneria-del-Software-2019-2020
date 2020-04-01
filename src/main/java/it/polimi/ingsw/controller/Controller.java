package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.view.View;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class Controller implements Observer {
    Model model;
    View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void chooseWorker(int workerIndex) {
        model.setCurrentWorker(model.getCurrentPlayer().getWorkers()[workerIndex]);
    }

    public void setup() {

    }

    public void game(Cell nextCell) {
        model.getCurrentWorker().turn(nextCell);
        model.setGameOver(model.getCurrentWorker().winCondition());
        if (model.getGameOver()) {
            notify();   //TODO: controllare qui
        }
        for (Player player : model.getPlayers()) {
            for (Worker worker : player.getWorkers()) {
                worker.setCanBeUsed(worker.checkSurroundingCells(model.getMap()));
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
        if (arg instanceof Integer) {
            model.setNumberOfPlayers((Integer) arg);
        }
        else if (arg instanceof Player) {
            model.addPlayer((Player) arg);
        }
        else if (arg instanceof ArrayList) {
            model.setAvailableGodList((ArrayList<GodList>) arg);
        }
        else if (arg instanceof GodList) {

        }
    }
}
