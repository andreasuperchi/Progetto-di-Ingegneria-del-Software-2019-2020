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
    Cell baseWorkerCell, nextWorkerCell;

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
        player2.setWorkers(GodName.ZEUS);
        Model.setCurrentPlayer(player1);
    }

    @Test
    public void checkIfIWinWithFiveTowers() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        player1.getWorkers()[0].setCurrentWorkerCell(baseWorkerCell);
        Model.getMap().setCompletedTowers(5);
        assertTrue(player1.getWorkers()[0].winCondition());
    }

    @Test
    public void checkNormalWinConditions() {
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        baseWorkerCell.setLevel(2);
        nextWorkerCell.setLevel(3);
        player1.getWorkers()[0].setCurrentWorkerCell(baseWorkerCell);
        player1.getWorkers()[0].move(nextWorkerCell);
        assertTrue(player1.getWorkers()[0].winCondition());
    }
}