//package it.polimi.ingsw.model.specialworkers;
//
//import it.polimi.ingsw.model.*;
//import org.junit.Before;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class WorkerApolloTest {
//    Model model;
//    Player player, opponent;
//    WorkerApollo workerApollo;
//    Worker workerOpponent;
//    Cell baseWorkerCell, nextWorkerCell;
//
//    @Before
//    public void setUp() {
//        model = new Model();
//        player = new Player("Test", 23, "@");
//        opponent = new Player("Enemy", 25, "#");
//        player.setWorkers(GodName.APOLLO);
//        opponent.setWorkers(GodName.TRITON);
//        workerApollo = (WorkerApollo) player.getWorkers()[0];
//        workerOpponent = opponent.getWorkers()[0];
//        Model.setCurrentPlayer(player);
//        baseWorkerCell = Model.getMap().getGrid()[1][1];
//        nextWorkerCell = Model.getMap().getGrid()[1][2];
//    }
//
//    @Test
//    public void checkMoveWithAnotherWorker() {
//        workerApollo.setCurrentWorkerCell(baseWorkerCell);
//        workerOpponent.setCurrentWorkerCell(nextWorkerCell);
//        workerApollo.move(nextWorkerCell);
//        assertEquals(nextWorkerCell, workerApollo.getCurrentWorkerCell());
//        assertEquals(baseWorkerCell, workerOpponent.getCurrentWorkerCell());
//    }
//
//    @Test
//    public void checkMoveWithoutAnotherWorker() {
//        workerApollo.setCurrentWorkerCell(baseWorkerCell);
//        workerApollo.move(nextWorkerCell);
//        assertEquals(nextWorkerCell, workerApollo.getCurrentWorkerCell());
//    }
//
//    @Test
//    public void checkIfBothWorkersAreMine() {
//        Model.getAvailableGods().add(GodName.APOLLO);
//        workerApollo = (WorkerApollo) player.getWorkers()[0];
//        workerApollo.setCurrentWorkerCell(baseWorkerCell);
//        player.getWorkers()[1].setCurrentWorkerCell(nextWorkerCell);
//        assertFalse(workerApollo.checkMove(nextWorkerCell));
//    }
//}