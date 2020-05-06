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