package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.*;
import it.polimi.ingsw.view.Observer;

public class Controller implements Observer<Choice> {
    Model model;

    /**
     * builds a new controller
     *
     * @param model is the model that is going to be linked with this controller
     */
    public Controller(Model model) {
        this.model = model;
    }

    /**
     * invokes the doAction method of the Model class when receives a choice
     *
     * @param message is the actual choice that is passed at the Model object
     */
    @Override
    public void update(Choice message) {
        if (message instanceof IntChoice) {
            model.doAction((IntChoice) message);
        }
    }
}
