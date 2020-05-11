//package it.polimi.ingsw.model.specialworkers;
//
//import it.polimi.ingsw.model.Cell;
//import it.polimi.ingsw.model.GodName;
//import it.polimi.ingsw.model.Model;
//import it.polimi.ingsw.model.Player;
//
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class WorkerChronusTest {
//
//    Model model;
//    Player player;
//    Cell baseWorkerCell, nextWorkerCell;
//
//    @Before
//    public void setUp() {
//        model = new Model();
//        player = new Player("Test", 55, "@");
//        player.setWorkers(GodName.CHRONUS);
//        Model.setCurrentPlayer(player);
//    }
//
//    @Test
//    public void checkIfIWinWithFiveTowers() {
//        baseWorkerCell = Model.getMap().getGrid()[1][1];
//        player.getWorkers()[0].setCurrentWorkerCell(baseWorkerCell);
//        Model.getMap().setCompletedTowers(5);
//        assertTrue(player.getWorkers()[0].winCondition());
//    }
//
//    @Test
//    public void checkNormalWinConditions() {
//        baseWorkerCell = Model.getMap().getGrid()[1][1];
//        nextWorkerCell = Model.getMap().getGrid()[1][2];
//        baseWorkerCell.setLevel(2);
//        nextWorkerCell.setLevel(3);
//        player.getWorkers()[0].setCurrentWorkerCell(baseWorkerCell);
//        player.getWorkers()[0].move(nextWorkerCell);
//        assertTrue(player.getWorkers()[0].winCondition());
//    }
//}