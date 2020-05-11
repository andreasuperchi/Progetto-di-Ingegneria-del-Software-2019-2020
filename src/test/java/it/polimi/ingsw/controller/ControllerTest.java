package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.choices.IntChoice;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ControllerTest {
    Model model;
    Controller controller;
    IntChoice intChoice;
    Player player;

    @Before
    public void setUp() {
        model = new Model();
        controller = new Controller(model);
        player = new Player("Test", 134, "@");
        intChoice = new IntChoice(player, 3);
    }

    @Test
    public void doActionTest() {
        Model.setCurrentPlayer(player);
        controller.update(intChoice);

        assertEquals(3, model.getNumberOfPlayers());
    }
}