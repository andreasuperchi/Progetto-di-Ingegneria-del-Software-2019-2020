package it.polimi.ingsw.model;

import it.polimi.ingsw.model.specialworkers.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GodNameTest {
    Player player;

    /**
     * instantiates a player
     */
    @Before
    public void setUp() {
        player = new Player("Test", 22, "@");
    }

    /**
     * checks the correct input of the string "Apollo" to the relative GodName
     */
    @Test
    public void checkParseInputApollo() {
        assertEquals(GodName.APOLLO, GodName.parseInput("Apollo"));
    }

    /**
     * checks the correct input of the string "Artemis" to the relative GodName
     */
    @Test
    public void checkParseInputArtemis() {
        assertEquals(GodName.ARTEMIS, GodName.parseInput("Artemis"));
    }

    /**
     * checks the correct input of the string "Athena" to the relative GodName
     */
    @Test
    public void checkParseInputAthena() {
        assertEquals(GodName.ATHENA, GodName.parseInput("Athena"));
    }

    /**
     * checks the correct input of the string "Atlas" to the relative GodName
     */
    @Test
    public void checkParseInputAtlas() {
        assertEquals(GodName.ATLAS, GodName.parseInput("Atlas"));
    }

    /**
     * checks the correct input of the string "Charon" to the relative GodName
     */
    @Test
    public void checkParseInputCharon() {
        assertEquals(GodName.CHARON, GodName.parseInput("Charon"));
    }

    /**
     * checks the correct input of the string "Chronus" to the relative GodName
     */
    @Test
    public void checkParseInputChronus() {
        assertEquals(GodName.CHRONUS, GodName.parseInput("Chronus"));
    }

    /**
     * checks the correct input of the string "Demeter" to the relative GodName
     */
    @Test
    public void checkParseInputDemeter() {
        assertEquals(GodName.DEMETER, GodName.parseInput("Demeter"));
    }

    /**
     * checks the correct input of the string "Hephaestus" to the relative GodName
     */
    @Test
    public void checkParseInputHephaestus() {
        assertEquals(GodName.HEPHAESTUS, GodName.parseInput("Hephaestus"));
    }

    /**
     * checks the correct input of the string "Hestia" to the relative GodName
     */
    @Test
    public void checkParseInputHestia() {
        assertEquals(GodName.HESTIA, GodName.parseInput("Hestia"));
    }

    /**
     * checks the correct input of the string "Minotaur" to the relative GodName
     */
    @Test
    public void checkParseInputMinotaur() {
        assertEquals(GodName.MINOTAUR, GodName.parseInput("Minotaur"));
    }

    /**
     * checks the correct input of the string "Pan" to the relative GodName
     */
    @Test
    public void checkParseInputPan() {
        assertEquals(GodName.PAN, GodName.parseInput("Pan"));
    }

    /**
     * checks the correct input of the string "Prometheus" to the relative GodName
     */
    @Test
    public void checkParseInputPrometheus() {
        assertEquals(GodName.PROMETHEUS, GodName.parseInput("Prometheus"));
    }

    /**
     * checks the correct input of the string "Triton" to the relative GodName
     */
    @Test
    public void checkParseInputTriton() {
        assertEquals(GodName.TRITON, GodName.parseInput("Triton"));
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Apollo", the worker that is instantiated is an instance of WorkerApollo
     */
    @Test
    public void checkParseGodApollo() {
        GodName godName = GodName.APOLLO;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerApollo);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Artemis", the worker that is instantiated is an instance of WorkerArtemis
     */
    @Test
    public void checkParseGodArtemis() {
        GodName godName = GodName.ARTEMIS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerArtemis);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Athena", the worker that is instantiated is an instance of WorkerAthena
     */
    @Test
    public void checkParseGodAthena() {
        GodName godName = GodName.ATHENA;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerAthena);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Atlas", the worker that is instantiated is an instance of WorkerAtlas
     */
    @Test
    public void checkParseGodAtlas() {
        GodName godName = GodName.ATLAS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerAtlas);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Charon", the worker that is instantiated is an instance of WorkerCharon
     */
    @Test
    public void checkParseGodCharon() {
        GodName godName = GodName.CHARON;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerCharon);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Chronus", the worker that is instantiated is an instance of WorkerChronus
     */
    @Test
    public void checkParseGodChronus() {
        GodName godName = GodName.CHRONUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerChronus);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Demeter", the worker that is instantiated is an instance of WorkerDemeter
     */
    @Test
    public void checkParseGodDemeter() {
        GodName godName = GodName.DEMETER;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerDemeter);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Hephaestus", the worker that is instantiated is an instance of WorkerHephaestus
     */
    @Test
    public void checkParseGodHephaestus() {
        GodName godName = GodName.HEPHAESTUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerHephaestus);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Hestia", the worker that is instantiated is an instance of WorkerHestia
     */
    @Test
    public void checkParseGodHestia() {
        GodName godName = GodName.HESTIA;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerHestia);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Minotaur", the worker that is instantiated is an instance of WorkerMinotaur
     */
    @Test
    public void checkParseGodMinotaur() {
        GodName godName = GodName.MINOTAUR;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerMinotaur);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Pan", the worker that is instantiated is an instance of WorkerPan
     */
    @Test
    public void checkParseGodPan() {
        GodName godName = GodName.PAN;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerPan);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Prometheus", the worker that is instantiated is an instance of WorkerPrometheus
     */
    @Test
    public void checkParseGodPrometheus() {
        GodName godName = GodName.PROMETHEUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerPrometheus);
    }

    /**
     * checks the correct working of the method parseGod. controls that
     * given the GodName "Triton", the worker that is instantiated is an instance of WorkerTriton
     */
    @Test
    public void checkParseGodTriton() {
        GodName godName = GodName.TRITON;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerTriton);
    }

    /**
     * checks that parseInput throws an IllegalArgumentException with the string "Test"
     * (because it's not a valid god)
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkParseFail() {
        GodName godName = GodName.parseInput("Test");
        godName.parseGod();
    }
}