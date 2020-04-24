package it.polimi.ingsw.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OutcomeTest {
    Outcome outcome;

    @Test
    public void checkWin() {
        outcome = Outcome.WIN;
        assertEquals("You Win!", outcome.printOutcome());
    }

    @Test
    public void checkLose() {
        outcome = Outcome.LOSE;
        assertEquals("You Lose!", outcome.printOutcome());
    }

    @Test
    public void checkInvalidDirection() {
        outcome = Outcome.INVALID_DIRECTION;
        assertEquals("The direction you chose is invalid!", outcome.printOutcome());
    }

    @Test
    public void checkMoveCompleted() {
        outcome = Outcome.MOVE_COMPLETED;
        assertEquals("You have successfully moved!", outcome.printOutcome());
    }

    @Test
    public void checkBuildCompleted() {
        outcome = Outcome.BUILD_COMPLETED;
        assertEquals("You have successfully built!", outcome.printOutcome());
    }

    @Test
    public void checkMoveAgain() {
        outcome = Outcome.MOVE_AGAIN;
        assertEquals("Do you want to move again? Answer with y/n", outcome.printOutcome());
    }

    @Test
    public void checkBuildAgain() {
        outcome = Outcome.BUILD_AGAIN;
        assertEquals("Do you want to build again? Answer with y/n", outcome.printOutcome());
    }

    @Test
    public void checkInvalidGod() {
        outcome = Outcome.INVALID_GOD;
        assertEquals("You can't use this god!", outcome.printOutcome());
    }

    @Test
    public void checkDuplicateGod() {
        outcome = Outcome.DUPLICATE_GOD;
        assertEquals("This god has already been selected!", outcome.printOutcome());
    }

    @Test
    public void checkUnavailableWorker() {
        outcome = Outcome.UNAVAILABLE_WORKER;
        assertEquals("Selected worker is unavailable!", outcome.printOutcome());
    }
}