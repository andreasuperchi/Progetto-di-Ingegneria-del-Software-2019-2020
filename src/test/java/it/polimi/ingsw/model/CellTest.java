package it.polimi.ingsw.model;


import org.junit.Before;
import org.junit.Test;

public class CellTest {
    Player player;
    Model model;
    Cell cell;

    @Before
    public void setUp() {
        model = new Model();
        cell = Model.getMap().getGrid()[1][2];
        player = new Player("Test", 43, "#");
    }

    @Test
    public void testToStringEmpty() {
        cell.setLevel(2);
        System.out.println(cell.toString());
    }

    @Test
    public void testToString() {
        cell.setLevel(2);
        player.setWorkers(GodName.APOLLO);
        player.getWorkers()[0].setCurrentWorkerCell(cell);
        System.out.println(cell.toString());
    }
}