package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.IntChoice;
import it.polimi.ingsw.model.specialworkers.WorkerArtemis;
import it.polimi.ingsw.model.specialworkers.WorkerDemeter;
import it.polimi.ingsw.model.specialworkers.WorkerPan;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ModelTest {
    Model model;
    Player player1, player2, player3;
    IntChoice input;

    /**
     * Initializes a game with 3 players and sets player1 as the current player
     */
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

    /**
     * tests the scenario where a player tries to do something but it's not his turn
     */
    @Test
    public void invalidCurrentPlayerTest() {
        input = new IntChoice(player2, 2);
        model.doAction(input);
        assertEquals(Outcome.INVALID_PLAYER, model.getOutcome());
    }

    /**
     * tests the scenario where a player types in a wrong number to select a god
     */
    @Test
    public void invalidAvailableGodsTest() {
        input = new IntChoice(player1, 27);
        model.doAction(input);
        assertEquals(Outcome.AVAILABLE_GODS_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where a player selects a god
     */
    @Test
    public void availableGodsTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        assertTrue(Model.getAvailableGods().contains(GodName.PAN));
        assertEquals(Model.turnPhase.AVAILABLE_GODS, model.getCurrentPhase());
    }

    /**
     * tests the scenario where the first player selects all the three gods
     */
    @Test
    public void fullAvailableGodsTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        assertTrue(Model.getAvailableGods().contains(GodName.PAN));
        assertTrue(Model.getAvailableGods().contains(GodName.DEMETER));
        assertTrue(Model.getAvailableGods().contains(GodName.ARTEMIS));
        assertEquals(Model.turnPhase.GOD_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.GOD_CHOICE_MENU, model.getOutcome());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player tries to select a god that is not available for this game
     */
    @Test
    public void invalidGodChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        assertEquals(Outcome.GOD_CHOICE_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where a player correctly selects a god from the available ones and checks
     * that the correct type of workers is associated with him
     */
    @Test
    public void godChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        assertTrue(player2.getWorkers()[0] instanceof WorkerDemeter);
        assertTrue(player2.getWorkers()[1] instanceof WorkerDemeter);
        assertEquals(Outcome.GOD_CHOICE_MENU, model.getOutcome());
        assertEquals(player3, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where all the three players select one god from the available ones, checking that
     * each player is associated with the correct god
     */
    @Test
    public void fullGodChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        assertTrue(Model.getAvailableGods().contains(GodName.PAN));
        assertTrue(Model.getAvailableGods().contains(GodName.DEMETER));
        assertTrue(Model.getAvailableGods().contains(GodName.ARTEMIS));


        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        assertTrue(player2.getWorkers()[0] instanceof WorkerDemeter);
        assertTrue(player2.getWorkers()[1] instanceof WorkerDemeter);
        assertTrue(player3.getWorkers()[0] instanceof WorkerPan);
        assertTrue(player3.getWorkers()[1] instanceof WorkerPan);
        assertTrue(player1.getWorkers()[0] instanceof WorkerArtemis);
        assertTrue(player1.getWorkers()[1] instanceof WorkerArtemis);
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
        assertEquals(Outcome.WORKERS_PLACEMENT_MENU, model.getOutcome());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player selects a cell that is out of the map to place his worker
     */
    @Test
    public void invalidWorkerPlacementTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 26);
        model.doAction(input);

        assertEquals(Outcome.WORKERS_PLACEMENT_ERROR, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
    }

    /**
     * tests the scenario where a player correctly places his first worker on the board
     */
    @Test
    public void firstWorkerPlacementTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);

        assertEquals(Model.getMap().getGrid()[3][0], player2.getWorkers()[0].getCurrentWorkerCell());
        assertEquals(Outcome.WORKERS_PLACEMENT_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
    }

    /**
     * tests the scenario where the first player correctly positions both of his workers on the board and
     * checks that the new current player is player3
     */
    @Test
    public void secondWorkerPlacementTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);

        assertEquals(Model.getMap().getGrid()[2][2], player2.getWorkers()[1].getCurrentWorkerCell());
        assertEquals(Model.getMap().getGrid()[3][0], player2.getWorkers()[0].getCurrentWorkerCell());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
        assertEquals(player3, Model.getCurrentPlayer());
        assertEquals(Outcome.WORKERS_PLACEMENT_MENU, model.getOutcome());
    }

    /**
     * tests the scenario where a player tries to place a worker on a cell that is occupied by another worker
     */
    @Test
    public void occupiedWorkerPlacementTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 16);
        model.doAction(input);

        assertEquals(Model.getMap().getGrid()[3][0], player2.getWorkers()[0].getCurrentWorkerCell());
        assertEquals(Model.turnPhase.WORKER_PLACEMENT, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
        assertEquals(Outcome.WORKERS_PLACEMENT_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where all the three players correctly place their workers on the board
     */
    @Test
    public void fullWorkerPlacementTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 7);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.WORKER_MENU, model.getOutcome());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player makes a mistake selecting one of his workers
     */
    @Test
    public void invalidWorkerChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Outcome.WORKER_CHOICE_ERROR, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
    }

    /**
     * tests the scenario where a player tries to select a worker that can't be used in this moment and checks
     * that the current player can choose another one
     */
    @Test
    public void cantBeUsedWorkerChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        player2.getWorkers()[0].setCanBeUsed(false);
        model.doAction(input);

        assertEquals(Outcome.UNAVAILABLE_WORKER, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
    }

    /**
     * tests the scenario where a player correctly selects one of his workers and checks
     * that the selected worker becomes the current worker in the model
     */
    @Test
    public void workerChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2.getWorkers()[0], model.getCurrentWorker());
    }

    /**
     * tests the scenario where a player types in a wrong number for the selection of the action
     * and checks that the current player can make another choice
     */
    @Test
    public void invalidActionChoice() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        assertEquals(Outcome.ACTION_CHOICE_ERROR, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
    }

    /**
     * tests the scenario where a player chooses a valid action
     */
    @Test
    public void actionChoiceTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Model.turnPhase.MOVE, model.getCurrentPhase());
        assertEquals(Outcome.DIRECTION_MENU, model.getOutcome());
    }

    /**
     * tests the scenario where a player, after having choose to move, types in an invalid direction
     */
    @Test
    public void invalidMoveTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 16);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 9);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.DIRECTION_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where a player, after having choose to move, tries to move towards a cell
     * that is out of the map
     */
    @Test
    public void outOfMapMoveTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 2);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.OUT_OF_MAP, model.getOutcome());
    }

    /**
     * tests the scenario where a player correctly moves one of his workers from a level 0 cell to
     * another level 0 cell, checking that this move is not making him win
     */
    @Test
    public void validMoveTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.getMap().getGrid()[0][1], player2.getWorkers()[0].currentWorkerCell);
        assertFalse(model.isGameOver());
    }

    /**
     * tests the scenario where a player correctly moves one of his workers from a level 2 cell to
     * a level 3 cell, checking that this move is making him win the game
     */
    @Test
    public void validMoveWinTest() {
        Model.getMap().getGrid()[0][0].setLevel(2);
        Model.getMap().getGrid()[0][1].setLevel(3);

        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Model.turnPhase.MOVE, model.getCurrentPhase());
        assertEquals(Model.getMap().getGrid()[0][1], player2.getWorkers()[0].currentWorkerCell);
        assertTrue(model.isGameOver());
    }

    /**
     * tests the scenario where a player is trying to build without moving,
     * checking that the player can repeat his choice
     */
    @Test
    public void cantBuildTest() {
        Model.getMap().getGrid()[0][0].setLevel(2);
        Model.getMap().getGrid()[0][1].setLevel(3);

        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.PROCESS_ACTION_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where a player, after having choose the build option, selects a direction
     * that doesn't exist and checks that the player can make another choice
     */
    @Test
    public void invalidBuildTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 10);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.DIRECTION_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where a player is trying to build out of the map, checking
     * that the player can make another choice
     */
    @Test
    public void outOfMapBuildTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.OUT_OF_MAP, model.getOutcome());
    }

    /**
     * tests the scenario where a player correctly moves, checking that the player is brought back
     * to the action menu
     */
    @Test
    public void validBuildTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(1, Model.getMap().getGrid()[1][1].getLevel());
    }

    /**
     * tests the scenario where a player, after having moved, is trying to go directly to the end-turn,
     * without having built anything
     */
    @Test
    public void cantGoToEndTurnTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(Outcome.PROCESS_ACTION_ERROR, model.getOutcome());
    }

    /**
     * tests the scenario where a player, after having moved and built, correctly goes to the end turn
     */
    @Test
    public void canGoToEndTurnTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        assertEquals(Model.turnPhase.END_TURN, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player, after having moved and built, goes to the end-turn but
     * types in a wrong number for the confirmation of the end-turn itself
     */
    @Test
    public void invalidEndTurn() {

        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Outcome.PROCESS_ACTION_ERROR, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
    }

    /**
     * tests the scenario where a player, after having moved and built, goes to the end-turn and confirms
     * that he wants to effectively ends his turn
     */
    @Test
    public void confirmEndTurnTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Outcome.WORKER_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
        assertEquals(player3, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player, after having moved and built, goes to the end-turn and gives
     * a negative answer at the end-turn confirmation
     */
    @Test
    public void dontConfirmEndTurnTest() {

        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);
        input = new IntChoice(player2, 5);
        model.doAction(input);
        input = new IntChoice(player3, 6);
        model.doAction(input);
        input = new IntChoice(player3, 8);
        model.doAction(input);
        input = new IntChoice(player1, 9);
        model.doAction(input);
        input = new IntChoice(player1, 11);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 7);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 7);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);


        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player tries to use a special power, checking
     * that the player is brought back to the action menu
     */
    @Test
    public void specialPowerTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 7);
        model.doAction(input);

        assertEquals(Outcome.ACTION_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player tries to use the special power choosing a cell
     * in a direction that is out of the map
     */
    @Test
    public void outOfMapSpecialPowerTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Outcome.OUT_OF_MAP, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player tries to use the special power in a forbidden
     * way
     */
    @Test
    public void wrongDirectionSpecialPowerTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        assertEquals(Outcome.DIRECTION_ERROR, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player tries to use twice a special power and checks
     * that the player is brought back to the action menu
     */
    @Test
    public void doubleUseSpecialPowerTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 7);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        assertEquals(Outcome.USED_SPECIAL_POWER, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player tries to use a special power of a worker that doesn't have it
     */
    @Test
    public void noSpecialPower() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);
        input = new IntChoice(player2, 5);
        model.doAction(input);
        input = new IntChoice(player3, 14);
        model.doAction(input);
        input = new IntChoice(player3, 15);
        model.doAction(input);
        input = new IntChoice(player1, 20);
        model.doAction(input);
        input = new IntChoice(player1, 21);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);
        
        input = new IntChoice(player2, 3);
        model.doAction(input);


        assertEquals(Outcome.NO_SPECIAL_POWER, model.getOutcome());
        assertEquals(Model.turnPhase.ACTION_CHOICE, model.getCurrentPhase());
        assertEquals(player2, Model.getCurrentPlayer());
    }

    /**
     * tests the scenario where a player has both of his workers unavailable, so he his
     * skipped during the turn transition
     */
    @Test
    public void gameOverTest() {
        input = new IntChoice(player1, 11);
        model.doAction(input);
        input = new IntChoice(player1, 7);
        model.doAction(input);
        input = new IntChoice(player1, 2);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);
        input = new IntChoice(player3, 1);
        model.doAction(input);
        input = new IntChoice(player1, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);
        input = new IntChoice(player2, 13);
        model.doAction(input);
        input = new IntChoice(player3, 10);
        model.doAction(input);
        input = new IntChoice(player3, 21);
        model.doAction(input);
        input = new IntChoice(player1, 3);
        model.doAction(input);
        input = new IntChoice(player1, 22);
        model.doAction(input);


        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        input = new IntChoice(player2, 3);
        model.doAction(input);

        input = new IntChoice(player2, 2);
        model.doAction(input);

        input = new IntChoice(player2, 5);
        model.doAction(input);

        input = new IntChoice(player2, 4);
        model.doAction(input);

        Model.getMap().getGrid()[0][4].setLevel(4);
        Model.getMap().getGrid()[0][3].setLevel(4);
        Model.getMap().getGrid()[1][3].setLevel(4);
        Model.getMap().getGrid()[2][3].setLevel(4);
        Model.getMap().getGrid()[2][4].setLevel(4);

        Model.getMap().getGrid()[3][0].setLevel(4);
        Model.getMap().getGrid()[3][1].setLevel(4);
        Model.getMap().getGrid()[4][1].setLevel(4);

        input = new IntChoice(player2, 1);
        model.doAction(input);

        assertEquals(Outcome.WORKER_MENU, model.getOutcome());
        assertEquals(Model.turnPhase.WORKER_CHOICE, model.getCurrentPhase());
        assertEquals(player1, Model.getCurrentPlayer());
    }
}