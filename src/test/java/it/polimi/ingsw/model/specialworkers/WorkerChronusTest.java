package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.Cell;
import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerChronusTest {

    Model model;
    Player player1, player2, player3;
    WorkerChronus workerChronus;
    Cell baseWorkerCell, nextWorkerCell;

    /**
     * initializes a game with 3 players, assigns to 2 of them Chronus and Pan,
     * instantiates the model object and sets the player1 as the current player.
     */
    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        player1.setWorkers(GodName.CHRONUS);
        player2.setWorkers(GodName.PAN);
        Model.setCurrentPlayer(player1);
        workerChronus = (WorkerChronus) player1.getWorkers()[0];
    }

    /**
     * checks that the worker satisfies the win conditions if
     * there are 5 completed towers on the map (5 domes)
     */
    @Test
    public void checkIfIWinWithFiveTowers() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        workerChronus.setCurrentWorkerCell(baseWorkerCell);
        Model.getMap().setCompletedTowers(5);
        assertTrue(player1.getWorkers()[0].winCondition());
    }

    /**
     * tests the standard win conditions
     */
    @Test
    public void checkNormalWinConditions() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        baseWorkerCell.setLevel(2);
        nextWorkerCell.setLevel(3);
        workerChronus.setCurrentWorkerCell(baseWorkerCell);
        workerChronus.move(nextWorkerCell);
        assertTrue(workerChronus.winCondition());
    }
}