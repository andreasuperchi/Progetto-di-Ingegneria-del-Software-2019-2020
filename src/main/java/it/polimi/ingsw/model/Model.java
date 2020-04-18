package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.DirectionPlayerChoice;
import it.polimi.ingsw.model.choices.NumberOfPlayersChoice;
import it.polimi.ingsw.model.choices.PlayerGodChoice;
import it.polimi.ingsw.model.choices.WorkerPlayerChoice;
import it.polimi.ingsw.view.Observable;

import java.util.ArrayList;

public class Model extends Observable<Model> implements Cloneable {
    private int numberOfPlayers;
    private ArrayList<GodName> availableGods;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Worker currentWorker;
    private boolean gameOver;
    private static Map map;
    private Outcome outcome;

    public Model() {
        availableGods = new ArrayList<GodName>();
        players = new ArrayList<Player>();
        gameOver = false;
        map = new Map();
    }

    public void setNumberOfPlayers(NumberOfPlayersChoice numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers.getNumberOfPlayers();
        notify(this);
    }

    public void setPlayerGod(PlayerGodChoice playerGodChoice) { //assegna a un giocatore il suo Dio e ne istanzia i rispettivi lavoratori
        if (availableGods.contains(playerGodChoice.getGodName())) {
            playerGodChoice.getPlayer().getWorkers()[0] = playerGodChoice.getGodName().parseGod(playerGodChoice.getPlayer());
            playerGodChoice.getPlayer().getWorkers()[1] = playerGodChoice.getGodName().parseGod(playerGodChoice.getPlayer());
            availableGods.remove(playerGodChoice.getGodName());
        }
        else {
            outcome = Outcome.INVALID_GOD;
        }
        notify(this);
    }

    public void setAvailableGods(PlayerGodChoice playerGodChoice) { //aggiunge un Dio alla lista di dèi disponibili
        availableGods.add(playerGodChoice.getGodName());
        notify(this);
    }

    public void setDirectionMove(DirectionPlayerChoice directionPlayerChoice) {
        Cell nextCell;
        try {
            nextCell = map.getNextWorkerCell(currentWorker.getCurrentWorkerCell(), directionPlayerChoice.getDirection());
            if (currentWorker.checkMove(nextCell)) {
                currentWorker.move(nextCell);
                outcome = currentWorker.setMoveCompleted();
            } else {
                outcome = Outcome.INVALID_DIRECTION;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            outcome = Outcome.INVALID_DIRECTION;
        } finally {
            notify(this);
        }
    }

    public void setDirectionBuild(DirectionPlayerChoice directionPlayerChoice) {
        Cell nextCell;
        try {
            nextCell = map.getNextWorkerCell(currentWorker.getCurrentWorkerCell(), directionPlayerChoice.getDirection());
            if (currentWorker.checkBuild(nextCell)) {
                currentWorker.build(nextCell);
                outcome = currentWorker.setBuildCompleted();
            } else {
                outcome = Outcome.INVALID_DIRECTION;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            outcome = Outcome.INVALID_DIRECTION;
        } finally {
            notify(this);
        }
    }

    public void setCurrentWorker(WorkerPlayerChoice workerPlayerChoice) {
        this.currentWorker = this.currentPlayer.getWorkers()[workerPlayerChoice.getWorkerNumber()];
        notify(this);
    }


}

//TODO: controllo Player