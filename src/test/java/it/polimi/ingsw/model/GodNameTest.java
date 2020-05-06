package it.polimi.ingsw.model;

import it.polimi.ingsw.model.specialworkers.*;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GodNameTest {
    Player player;


    @Before
    public void setUp() {
        player = new Player("Test", 22, "@");
    }

    @Test
    public void checkParseInputApollo() {
        assertEquals(GodName.APOLLO, GodName.parseInput("Apollo"));
    }

    @Test
    public void checkParseInputArtemis() {
        assertEquals(GodName.ARTEMIS, GodName.parseInput("Artemis"));
    }

    @Test
    public void checkParseInputAthena() {
        assertEquals(GodName.ATHENA, GodName.parseInput("Athena"));
    }


    @Test
    public void checkParseInputAtlas() {
        assertEquals(GodName.ATLAS, GodName.parseInput("Atlas"));
    }

    @Test
    public void checkParseInputCharon() {
        assertEquals(GodName.CHARON, GodName.parseInput("Charon"));
    }

    @Test
    public void checkParseInputChronus() {
        assertEquals(GodName.CHRONUS, GodName.parseInput("Chronus"));
    }

    @Test
    public void checkParseInputDemeter() {
        assertEquals(GodName.DEMETER, GodName.parseInput("Demeter"));
    }

    @Test
    public void checkParseInputHephaestus() {
        assertEquals(GodName.HEPHAESTUS, GodName.parseInput("Hephaestus"));
    }

    @Test
    public void checkParseInputHestia() {
        assertEquals(GodName.HESTIA, GodName.parseInput("Hestia"));
    }

    @Test
    public void checkParseInputMinotaur() {
        assertEquals(GodName.MINOTAUR, GodName.parseInput("Minotaur"));
    }

    @Test
    public void checkParseInputPan() {
        assertEquals(GodName.PAN, GodName.parseInput("Pan"));
    }

    @Test
    public void checkParseInputPrometheus() {
        assertEquals(GodName.PROMETHEUS, GodName.parseInput("Prometheus"));
    }

    @Test
    public void checkParseInputTriton() {
        assertEquals(GodName.TRITON, GodName.parseInput("Triton"));
    }

    @Test
    public void checkParseInputZeus() {
        assertEquals(GodName.ZEUS, GodName.parseInput("Zeus"));
    }

    @Test
    public void checkParseGodApollo() {
        GodName godName = GodName.APOLLO;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerApollo);
    }

    @Test
    public void checkParseGodArtemis() {
        GodName godName = GodName.ARTEMIS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerArtemis);
    }


    @Test
    public void checkParseGodAthena() {
        GodName godName = GodName.ATHENA;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerAthena);
    }

    @Test
    public void checkParseGodAtlas() {
        GodName godName = GodName.ATLAS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerAtlas);
    }

    @Test
    public void checkParseGodCharon() {
        GodName godName = GodName.CHARON;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerCharon);
    }

    @Test
    public void checkParseGodChronus() {
        GodName godName = GodName.CHRONUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerChronus);
    }

    @Test
    public void checkParseGodDemeter() {
        GodName godName = GodName.DEMETER;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerDemeter);
    }

    @Test
    public void checkParseGodHephaestus() {
        GodName godName = GodName.HEPHAESTUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerHephaestus);
    }

    @Test
    public void checkParseGodHestia() {
        GodName godName = GodName.HESTIA;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerHestia);
    }

    @Test
    public void checkParseGodMinotaur() {
        GodName godName = GodName.MINOTAUR;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerMinotaur);
    }

    @Test
    public void checkParseGodPan() {
        GodName godName = GodName.PAN;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerPan);
    }

    @Test
    public void checkParseGodPrometheus() {
        GodName godName = GodName.PROMETHEUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerPrometheus);
    }

    @Test
    public void checkParseGodTriton() {
        GodName godName = GodName.TRITON;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerTriton);
    }

    @Test
    public void checkParseGodZeus() {
        GodName godName = GodName.ZEUS;
        Worker worker = godName.parseGod();
        assertTrue(worker instanceof WorkerZeus);
    }

    @Test
    public void checkPlayerParse() {
        Player player = new Player("Test", 45, "@");
        player.setWorkers(GodName.APOLLO);
        assertTrue(player.getWorkers()[0] instanceof WorkerApollo);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkParseFail() {
        GodName godName = GodName.parseInput("Test");
        godName.parseGod();
    }
}