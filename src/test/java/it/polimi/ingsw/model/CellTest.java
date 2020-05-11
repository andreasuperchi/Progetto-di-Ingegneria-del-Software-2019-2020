package it.polimi.ingsw.model;


import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class CellTest {
    Player player1, player2, player3;
    Model model;
    Cell cell;

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
        cell = Model.getMap().getGrid()[1][2];
    }

    @Test
    public void testToStringEmpty() {
        cell.setLevel(2);
        System.out.println(cell.toString());
    }

    @Test
    public void testToString() {
        cell.setLevel(2);
        player1.setWorkers(GodName.APOLLO);
        player1.getWorkers()[0].setCurrentWorkerCell(cell);
        System.out.println(cell.toString());
    }
}