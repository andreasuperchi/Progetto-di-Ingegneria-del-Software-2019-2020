package it.polimi.ingsw.model;

import org.junit.Before;
import org.junit.Test;

public class OutcomeTest {
    Outcome outcome;
    Model model;

//    @Test
//    public void checkWin() {
//        outcome = Outcome.WIN;
//        assertEquals("You Win!", outcome.printOutcome());
//    }
//
//    @Test
//    public void checkLose() {
//        outcome = Outcome.LOSE;
//        assertEquals("You Lose!", outcome.printOutcome());
//    }
//
//    @Test
//    public void checkInvalidGod() {
//        outcome = Outcome.INVALID_GOD;
//        assertEquals("You can't select this god!", outcome.printOutcome());
//    }
//
//    @Test
//    public void checkDuplicateGod() {
//        outcome = Outcome.DUPLICATE_GOD;
//        assertEquals("This god has already been selected!", outcome.printOutcome());
//    }
//
//    @Test
//    public void checkUnavailableWorker() {
//        outcome = Outcome.UNAVAILABLE_WORKER;
//        assertEquals("Selected worker is unavailable!", outcome.printOutcome());
//    }

//    @Test
//    public void idk() {
//        outcome = Outcome.AVAILABLE_GODS_MENU;
//        System.out.println(outcome.printOutcome());
//    }

    @Test
    public void checkGodChoiceMenu() {
        model = new Model();
        outcome = Outcome.GOD_CHOICE_MENU;
        Model.getAvailableGods().add(GodName.APOLLO);
        Model.getAvailableGods().add(GodName.ARTEMIS);
        Model.getAvailableGods().add(GodName.ZEUS);
        System.out.println(outcome.printOutcome());
    }
}