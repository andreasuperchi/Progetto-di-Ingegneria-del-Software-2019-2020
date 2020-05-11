package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.IntChoice;
import it.polimi.ingsw.model.specialworkers.WorkerTriton;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModelTest {
    Model model;
    Player player1, player2, player3;
    IntChoice input;


    @Before
    public void setUp() {

        player1 = new Player("Andre", 5, "@");
        player2 = new Player("Fra", 2, "#");
        player3 = new Player("Ale", 78, "$");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        players.add(player3);
        model = new Model(players, 3);
        Model.setCurrentPlayer(player1);
    }

    @Test
    public void invalidCurrentPlayerTest() {
        input = new IntChoice(player2, 2);
        model.doAction(input);
        assertEquals(Outcome.INVALID_PLAYER, model.getOutcome());
    }

    @Test
    public void invalidAvailableGodsTest() {

        input = new IntChoice(player1, 27);
        model.doAction(input);
        assertEquals(Outcome.INVALID_GOD, model.getOutcome());
    }

    @Test
    public void availableGodsTest() {
        input = new IntChoice(player1, 10);
        model.doAction(input);
        assertTrue(Model.getAvailableGods().contains(GodName.PAN));
        assertEquals(Model.turnPhase.AVAILABLE_GODS, model.getCurrentPhase());
    }

    @Test
    public void fullAvailableGodsTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        assertTrue(Model.getAvailableGods().contains(GodName.PAN));
        assertTrue(Model.getAvailableGods().contains(GodName.TRITON));
        assertTrue(Model.getAvailableGods().contains(GodName.ARTEMIS));
        assertEquals(Model.turnPhase.GOD_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.GOD_CHOICE_MENU, model.getOutcome());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    @Test
    public void duplicateAvailableGodsTest() {
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 10);
        model.doAction(input);
        assertEquals(Outcome.INVALID_GOD, model.getOutcome());
    }

    @Test
    public void invalidGodChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Outcome.INVALID_GOD, model.getOutcome());
    }

    @Test
    public void godChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertTrue(player2.getWorkers()[0] instanceof WorkerTriton);
        assertTrue(player2.getWorkers()[1] instanceof WorkerTriton);
        assertEquals(Outcome.GOD_CHOICE_MENU, model.getOutcome());
        assertEquals(player3, Model.getCurrentPlayer());
    }

    @Test
    public void fullGodChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
        assertEquals(Outcome.WORKERS_PLACEMENT_MENU, model.getOutcome());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    @Test
    public void invalidWorkerPlacementTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 25);
        model.doAction(input);

        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
    }

    @Test
    public void firstWorkerPlacementTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);

        assertEquals(Model.getMap().getGrid()[3][0], player2.getWorkers()[0].getCurrentWorkerCell());
        assertEquals(Outcome.WORKERS_PLACEMENT_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
    }

    @Test
    public void secondWorkerPlacementTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);

        assertEquals(Model.getMap().getGrid()[2][2], player2.getWorkers()[1].getCurrentWorkerCell());
        assertEquals(Model.getMap().getGrid()[3][0], player2.getWorkers()[0].getCurrentWorkerCell());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
        assertEquals(player3, Model.getCurrentPlayer());
        assertEquals(Outcome.WORKERS_PLACEMENT_MENU, model.getOutcome());
    }


    @Test
    public void occupiedWorkerPlacementTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 15);
        model.doAction(input);

        assertEquals(Model.getMap().getGrid()[3][0], player2.getWorkers()[0].getCurrentWorkerCell());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
    }

    @Test
    public void fullWorkerPlacementTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.WORKER_MENU, model.getOutcome());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    @Test
    public void invalidWorkerChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        assertEquals(Outcome.INVALID_WORKER, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
    }


    @Test
    public void cantBeUsedWorkerChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        player2.getWorkers()[0].setCanBeUsed(false);
        model.doAction(input);

        assertEquals(Outcome.UNAVAILABLE_WORKER, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
    }

    @Test
    public void workerChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2.getWorkers()[0], model.getCurrentWorker());
    }

    @Test
    public void invalidActionChoice() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        assertEquals(Outcome.INVALID_ACTION, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
    }


    @Test
    public void actionChoiceTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 15);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        assertEquals(Model.turnPhase.MOVE, model.getCurrentPhase());
        assertEquals(Outcome.DIRECTION_MENU, model.getOutcome());
    }

    @Test
    public void invalidMoveTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 8);
        model.doAction(input);

        assertEquals(Model.turnPhase.MOVE, model.getCurrentPhase());
        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
    }

    @Test
    public void outOfMapMoveTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        assertEquals(Model.turnPhase.MOVE, model.getCurrentPhase());
        assertEquals(Outcome.OUT_OF_MAP, model.getOutcome());
    }

    @Test
    public void validMoveTest() {

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.getMap().getGrid()[0][1], player2.getWorkers()[0].currentWorkerCell);
        assertFalse(model.isGameOver());
    }

    @Test
    public void validMoveWinTest() {
        Model.getMap().getGrid()[0][0].setLevel(2);
        Model.getMap().getGrid()[0][1].setLevel(3);

        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 0);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        assertEquals(Model.turnPhase.MOVE, model.getCurrentPhase());
        assertEquals(Model.getMap().getGrid()[0][1], player2.getWorkers()[0].currentWorkerCell);
        assertTrue(model.isGameOver());
    }

    @Test
    public void cantBuildTest() {
        Model.getMap().getGrid()[0][0].setLevel(2);
        Model.getMap().getGrid()[0][1].setLevel(3);

        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelta azione
        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
    }

    @Test
    public void invalidBuildTest() {

        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 9);
        model.doAction(input);

        assertEquals(Model.turnPhase.BUILD, model.getCurrentPhase());
        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
    }

    @Test
    public void outOfMapBuildTest() {

        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 0);
        model.doAction(input);

        assertEquals(Model.turnPhase.BUILD, model.getCurrentPhase());
        assertEquals(Outcome.OUT_OF_MAP, model.getOutcome());
    }

    @Test
    public void validBuildTest() {
        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 4);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(1, Model.getMap().getGrid()[1][1].getLevel());
    }

    @Test
    public void cantGoToEndTurnTest() {
        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di andare all'end turn
        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
    }

    @Test
    public void canGoToEndTurnTest() {
        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 4);
        model.doAction(input);
        //vado all'end turn
        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Model.turnPhase.END_TURN, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    @Test
    public void invalidEndTurn() {
        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 4);
        model.doAction(input);
        //vado all'end turn
        input = new IntChoice(player2, 3);
        model.doAction(input);
        //confermo end turn
        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Outcome.INVALID_INPUT, model.getOutcome());
        assertEquals(Model.turnPhase.END_TURN, model.getCurrentPhase());
    }

    @Test
    public void confirmEndTurnTest() {
        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 4);
        model.doAction(input);
        //vado all'end turn
        input = new IntChoice(player2, 3);
        model.doAction(input);
        //confermo end turn
        input = new IntChoice(player2, 0);
        model.doAction(input);

        assertEquals(Outcome.WORKER_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
        assertEquals(player3, Model.getCurrentPlayer());
    }

    @Test
    public void dontConfirmEndTurnTest() {
        //scelta degli dèi in gioco
        input = new IntChoice(player1, 10);
        model.doAction(input);
        input = new IntChoice(player1, 12);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);
        //scelta di un dio per ogni giocatore
        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 0);
        model.doAction(input);
        input = new IntChoice(player1, 0);
        model.doAction(input);
        //piazzamento worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        input = new IntChoice(player2, 12);
        model.doAction(input);
        input = new IntChoice(player3, 9);
        model.doAction(input);
        input = new IntChoice(player3, 20);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);
        //scelta del worker
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //scelgo di muovere
        input = new IntChoice(player2, 0);
        model.doAction(input);
        //muovo
        input = new IntChoice(player2, 2);
        model.doAction(input);
        //scelgo di costruire
        input = new IntChoice(player2, 1);
        model.doAction(input);
        //costruisco
        input = new IntChoice(player2, 4);
        model.doAction(input);
        //vado all'end turn
        input = new IntChoice(player2, 3);
        model.doAction(input);
        //confermo end turn
        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }
}