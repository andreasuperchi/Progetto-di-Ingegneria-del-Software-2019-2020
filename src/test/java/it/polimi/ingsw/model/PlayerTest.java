package it.polimi.ingsw.model;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    Player player;

    @Test
    public void testSymbol() {
        player = new Player("Test", 11, "@");
        player.setWorkers(GodName.APOLLO);
        assertEquals("@A", player.getWorkers()[0].symbol);
    }
}