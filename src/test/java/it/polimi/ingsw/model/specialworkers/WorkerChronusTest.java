package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

class WorkerChronusTest {

    Model model;
    Player player;
    GodChoice godChoice;
    Cell c1, c2;

    @BeforeEach
    void setUp() {
        model = new Model();
        player = new Player("Test", 55);
        godChoice = new GodChoice(player, GodName.CHRONUS);
    }

    @Test
    public void checkIfIWinWithFiveTowers() {
        model.setAvailableGods(godChoice);
        model.setPlayerGod(godChoice);
        c1 = Model.getMap().getGrid()[1][1];
        player.getWorkers()[0].setCurrentWorkerCell(c1);
        Model.getMap().setCompletedTowers(5);
        assertTrue(player.getWorkers()[0].winCondition());
    }

    @Test
    public void checkNormalWinConditions() {
        model.setAvailableGods(godChoice);
        model.setPlayerGod(godChoice);
        c1 = Model.getMap().getGrid()[1][1];
        c2 = Model.getMap().getGrid()[1][2];
        c1.setLevel(2);
        c2.setLevel(3);
        player.getWorkers()[0].setCurrentWorkerCell(c1);
        player.getWorkers()[0].move(c2);
        assertTrue(player.getWorkers()[0].winCondition());
    }
}