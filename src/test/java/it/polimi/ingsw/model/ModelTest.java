package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.DirectionChoice;
import it.polimi.ingsw.model.choices.GodChoice;

import static org.junit.Assert.assertTrue;

import it.polimi.ingsw.model.choices.NumberOfPlayersChoice;
import it.polimi.ingsw.model.choices.WorkerChoice;
import it.polimi.ingsw.model.specialworkers.WorkerApollo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ModelTest {

    Model model;
    Player player;
    NumberOfPlayersChoice numberOfPlayersChoice;
    GodChoice godChoice;
    WorkerChoice workerChoice;
    DirectionChoice directionChoice;
    Cell cell;


    @BeforeEach
    void setUp() {
        model = new Model();
        player = new Player("test", 2);
        Model.setCurrentPlayer(player);
        numberOfPlayersChoice = new NumberOfPlayersChoice(player, 3);
        godChoice = new GodChoice(player, GodName.APOLLO);
        model.getAvailableGods().add(GodName.APOLLO);
        workerChoice = new WorkerChoice(player, 0);
        directionChoice = new DirectionChoice(player, Direction.NORTH);
        cell = Model.getMap().getGrid()[0][0];

    }

    @Test
    public void checkSetNumberOfPlayers() {
        model.setNumberOfPlayers(numberOfPlayersChoice);
        assertEquals(model.getNumberOfPlayers(), 3);
    }


    @Test
    public void checkSetPlayerGodIn() {
        model.setPlayerGod(godChoice);
        assertTrue(godChoice.getPlayer().getWorkers()[0] instanceof WorkerApollo);
        assertTrue(godChoice.getPlayer().getWorkers()[1] instanceof WorkerApollo);

    }

    @Test
    public void checkSetPlayerGodOut() {
        godChoice = new GodChoice(player, GodName.ARTEMIS);
        model.setPlayerGod(godChoice);
        assertEquals(Outcome.INVALID_GOD, model.getOutcome());
    }

    @Test
    public void checkSetAvailableGodsDuplicated() {
        model.setAvailableGods(godChoice);
        assertEquals(Outcome.DUPLICATE_GOD, model.getOutcome());
    }

    @Test
    public void checkSetAvailableGodsNew() {
        godChoice = new GodChoice(player, GodName.ZEUS);
        model.setAvailableGods(godChoice);
        assertTrue(model.getAvailableGods().contains(GodName.ZEUS));
    }


    @Test
    public void checkSetCurrentWorkerAvailable() {
        model.setWorkerChoice(workerChoice);
        assertEquals(model.getCurrentWorker(), Model.getCurrentPlayer().getWorkers()[0]);
    }

    @Test
    public void checkSetCurrentWorkerUnavailable() {
        Model.getCurrentPlayer().getWorkers()[0].setCanBeUsed(false);
        model.setWorkerChoice(workerChoice);
        assertEquals(model.getOutcome(), Outcome.UNAVAILABLE_WORKER);
    }

    @Test
    public void checkSetDirectionMoveInvalid() {
        Model.getCurrentPlayer().getWorkers()[0].setCurrentWorkerCell(cell);
        model.setCurrentWorker(Model.getCurrentPlayer().getWorkers()[0]);
        model.setDirectionMove(directionChoice);
        assertEquals(Outcome.INVALID_DIRECTION, model.getOutcome());
    }
}