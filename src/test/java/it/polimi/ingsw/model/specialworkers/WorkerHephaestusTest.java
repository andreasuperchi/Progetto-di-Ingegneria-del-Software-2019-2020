package it.polimi.ingsw.model.specialworkers;

import it.polimi.ingsw.model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class WorkerHephaestusTest {

    Model model;
    Player player1, player2;
    WorkerHephaestus workerHephaestus;
    Cell baseWorkerCell, nextWorkerCell, otherCell;

    /**
     * sets the game with 2 players and their workers, creates instance of model
     * then set the current player
     */
    @Before
    public void setUp() {
        player1 = new Player("Test", 5, "@");
        player2 = new Player("Opponent", 2, "#");
        ArrayList<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        model = new Model(players, 2);
        player1.setWorkers(GodName.HEPHAESTUS);
        player2.setWorkers(GodName.PAN);
        workerHephaestus = (WorkerHephaestus) player1.getWorkers()[0];
        Model.setCurrentPlayer(player1);
        baseWorkerCell = Model.getMap().getGrid()[1][1];
        nextWorkerCell = Model.getMap().getGrid()[1][2];
        otherCell = Model.getMap().getGrid()[0][1];
    }


    /**
     * checks if workerHephaestus can use special power knowing has move and has build previously
     */
    @Test
    public void canUseSpecialPowerTrue() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.setHasMoved(true);
        workerHephaestus.build(nextWorkerCell);
        assertTrue(workerHephaestus.canUseSpecialPower());
    }

    /**
     * checks if workerHephaestus can't use special power knowing hasn't move and has build previously
     */
    @Test
    public void canUseSpecialPowerFalseNoMove() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        //no move
        workerHephaestus.build(nextWorkerCell);
        assertFalse(workerHephaestus.canUseSpecialPower());
    }

    /**
     * checks if workerHephaestus can't use special power knowing has move and hasn't build previously
     */
    @Test
    public void canUseSpecialPowerFalseNoBuild() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.setHasMoved(true);
        //no Build
        assertFalse(workerHephaestus.canUseSpecialPower());
    }

    /**
     * checks if workerHephaestus can't use special power knowing hasn't move and hasn't build previously
     */
    @Test
    public void canUseSpecialPowerFalseNoMoveNoBuild() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        //no move, no build
        assertFalse(workerHephaestus.canUseSpecialPower());
    }

    /**
     * checks if workerHephaestus can't build on nextWorkerCell knowing nextWorkerCell is occupied
     */
    @Test(expected = IllegalArgumentException.class)
    public void buildFalse() {
        nextWorkerCell.setIsOccupied(true);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        assertFalse(workerHephaestus.getHasBuilt());
    }

    /**
     * checks workerHephaestus can build a dome on nextWorkerCell (level = 4 and isOccuiped = true)
     */
    @Test
    public void buildDomeTrue() {
        nextWorkerCell.setLevel(3);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        assertEquals(4, nextWorkerCell.getLevel());
        assertTrue(nextWorkerCell.getIsOccupied());
    }

    /**
     * checks workerHephaestus can build a block different from a dome on nextWorkerCell
     */
    @Test
    public void buildNotDome() {
        nextWorkerCell.setLevel(1);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        assertEquals(2, nextWorkerCell.getLevel());
    }

    /**
     * checks the special power on the nextWorkerCell, knowing workerHephaestus has still built on that cell
     */
    @Test
    public void buildAgainTrue() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        workerHephaestus.specialPower(nextWorkerCell);
        assertEquals(2, nextWorkerCell.getLevel());

    }

    /**
     * checks that workerHephaestus cant'use special power on otherCell because has built on a different cell
     */
    @Test(expected = IllegalArgumentException.class)
    public void buildAgainFalse() {
        nextWorkerCell.setLevel(0);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell);
        //provo a fagli costruire su una cella diversa da nextWorkerCell (non puo!)
        workerHephaestus.specialPower(otherCell);
        assertEquals(1, nextWorkerCell.getLevel());
        assertEquals(0, otherCell.getLevel());
    }

    /**
     * checks that workerHephaestus cant'use special power on nextWorkerCell because
     * the level of that cell is 3 (workerHephaestus cant' build dome with the special power)
     */
    @Test(expected = IllegalArgumentException.class)
    public void buildDomeFalse() {
        nextWorkerCell.setLevel(2);
        workerHephaestus.setCurrentWorkerCell(baseWorkerCell);
        workerHephaestus.build(nextWorkerCell); //livello -> 3
        //provo a fagli costruire una cupola (non può!)
        workerHephaestus.specialPower(nextWorkerCell);
        assertEquals(3, nextWorkerCell.getLevel());
    }


}