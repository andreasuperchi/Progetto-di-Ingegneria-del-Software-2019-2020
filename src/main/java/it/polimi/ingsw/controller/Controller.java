package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.*;
import it.polimi.ingsw.model.choices.NumberOfPlayersChoice;
import it.polimi.ingsw.model.choices.PlayerChoice;
import it.polimi.ingsw.model.choices.PlayerGodChoice;
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

        }
    }
}
