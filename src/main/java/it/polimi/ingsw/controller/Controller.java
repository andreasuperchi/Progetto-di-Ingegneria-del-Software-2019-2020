package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.*;
import it.polimi.ingsw.view.Observer;

public class Controller implements Observer<Choice> {
    Model model;

    public Controller(Model model) {
        this.model = model;
    }

    @Override
    public void update(Choice message) {
        if (message instanceof IntChoice) {
            model.doAction((IntChoice) message);
        }
    }
}
