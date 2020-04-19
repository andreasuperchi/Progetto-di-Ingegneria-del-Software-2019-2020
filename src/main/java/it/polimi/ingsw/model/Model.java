package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.*;
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

    public ArrayList<GodName> getAvailableGods() {
        return availableGods;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(NumberOfPlayersChoice numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers.getNumberOfPlayers();
        notify(this);
    }

    public Worker getCurrentWorker() {
        return currentWorker;
    }

    public void setPlayerGod(PlayerGodChoice playerGodChoice) { //assegna a un giocatore il suo Dio e ne istanzia i rispettivi lavoratori
        if (availableGods.contains(playerGodChoice.getGodName())) {
            playerGodChoice.getPlayer().getWorkers()[0] = playerGodChoice.getGodName().parseGod(playerGodChoice.getPlayer());
            playerGodChoice.getPlayer().getWorkers()[1] = playerGodChoice.getGodName().parseGod(playerGodChoice.getPlayer());
            availableGods.set(availableGods.indexOf(playerGodChoice.getGodName()), GodName.SELECTED_GOD);
        } else {
            outcome = Outcome.INVALID_GOD;
        }
        notify(this);
    }

    public void setAvailableGods(PlayerGodChoice playerGodChoice) { //aggiunge un Dio alla lista di dèi disponibili
        if (availableGods.contains(playerGodChoice.getGodName())) {
            outcome = Outcome.DUPLICATE_GOD;
        } else {
            availableGods.add(playerGodChoice.getGodName());
        }
        notify(this);
    }

    //imposta il worker scelto per il turno
    public void setCurrentWorker(WorkerPlayerChoice workerPlayerChoice) {
        //controllo se il lavoratore è disponibile
        if (currentPlayer.getWorkers()[workerPlayerChoice.getWorkerNumber()].getCanBeUsed()) {
            this.currentWorker = this.currentPlayer.getWorkers()[workerPlayerChoice.getWorkerNumber()];
        } else {
            outcome = Outcome.UNAVAILABLE_WORKER;
        }
        notify(this);
    }

    public void setDirectionMove(DirectionPlayerChoice directionPlayerChoice) {
        Cell nextCell;
        try {
            nextCell = map.getNextWorkerCell(currentWorker.getCurrentWorkerCell(), directionPlayerChoice.getDirection());
            if (currentWorker.checkMove(nextCell)) {
                currentWorker.move(nextCell);
                if (getCurrentWorker().winCondition()) {
                    outcome = Outcome.WIN;
                    gameOver = true;
                } else {
                    outcome = currentWorker.setMoveCompleted();
                }
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
                if (getCurrentWorker().winCondition()) {
                    outcome = Outcome.WIN;
                    gameOver = true;
                } else {
                    outcome = currentWorker.setBuildCompleted();
                }
            } else {
                outcome = Outcome.INVALID_DIRECTION;
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            outcome = Outcome.INVALID_DIRECTION;
        } finally {
            notify(this);
        }
    }

    public void setCurrentWorkerHasMoved(StringChoice stringChoice) {
        if (stringChoice.getString().equals("y")) {
            currentWorker.setHasMoved(false);
        }
        notify(this);
    }

    public void setCurrentWorkerHasBuild(StringChoice stringChoice) {
        if (stringChoice.getString().equals("y")) {
            currentWorker.setHasBuilt(false);
        }
        notify(this);
    }

    public void endTurn() {
        currentWorker.setHasBuilt(false);
        currentWorker.setHasMoved(false);
        if (players.indexOf(currentPlayer) == numberOfPlayers - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }

        //TODO: CONTORLLO LAVORATORI DISPONIBILI NUOVO PLAYER

        notify(this);
    }


}

//TODO: controllo Player