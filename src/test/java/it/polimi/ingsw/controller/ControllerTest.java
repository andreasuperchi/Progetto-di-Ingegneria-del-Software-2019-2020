package it.polimi.ingsw.controller;

import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.model.choices.IntChoice;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ControllerTest {
    Model model;
    Controller controller;
    IntChoice intChoice;
    Player player;

    /**
     * initializes a game with 3 players and instantiates the model and the controller.
     * also creates an intChoice to be processed
     */
    @Before
    public void setUp() {
        Player player1 = new Player("Andre", 5, "@");
        Player player2 = new Player("Fra", 2, "#");
        Player player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        controller = new Controller(model);
        player = new Player("Test", 134, "@");
        intChoice = new IntChoice(player, 4);
    }

    /**
     * checks that the choice processing works correctly
     */
    @Test
    public void doActionTest() {
        Model.setCurrentPlayer(player);
        controller.update(intChoice);

        assertTrue(Model.getAvailableGods().contains(GodName.ATLAS));
    }
}