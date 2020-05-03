package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.*;
import it.polimi.ingsw.view.Observable;

import java.util.ArrayList;

public class Model extends Observable<Model> implements Cloneable {
    private int numberOfPlayers;
    private ArrayList<GodName> availableGods;
    private ArrayList<Player> players;
    private static Player currentPlayer;
    private Worker currentWorker;
    private boolean gameOver;
    private static Map map;

    private enum turnPhase {NUMBER_OF_PLAYERS, AVAILABLE_GODS, GOD_CHOICE, WORKER_CHOICE, ACTION_CHOICE, MOVE, BUILD, END_TURN}

    ;
    private turnPhase currentPhase;
    private Outcome outcome;


    public Model() {
        availableGods = new ArrayList<GodName>();
        players = new ArrayList<Player>();
        gameOver = false;
        currentPhase = turnPhase.NUMBER_OF_PLAYERS;
        map = new Map();
    }

    public static void setCurrentPlayer(Player currentPlayer) {
        Model.currentPlayer = currentPlayer;
    }

    public void setCurrentWorker(Worker currentWorker) {
        this.currentWorker = currentWorker;
    }

    public ArrayList<GodName> getAvailableGods() {
        return availableGods;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public static Map getMap() {
        return map;
    }

    public Worker getCurrentWorker() {
        return currentWorker;
    }

    public void setNumberOfPlayers(IntChoice numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers.getNumberOfPlayers();
        notify(this);
    }

    public void setPlayerGod(GodChoice godChoice) { //assegna a un giocatore il suo Dio e ne istanzia i rispettivi lavoratori
        if (availableGods.contains(godChoice.getGodName())) {
            godChoice.getPlayer().getWorkers()[0] = godChoice.getGodName().parseGod();
            godChoice.getPlayer().getWorkers()[1] = godChoice.getGodName().parseGod();
            availableGods.set(availableGods.indexOf(godChoice.getGodName()), GodName.SELECTED_GOD);
        } else {
            outcome = Outcome.INVALID_GOD;
        }
        notify(this);
    }

    public void setAvailableGods(GodChoice godChoice) { //aggiunge un Dio alla lista di dèi disponibili
        if (availableGods.contains(godChoice.getGodName())) {
            outcome = Outcome.DUPLICATE_GOD;
        } else {
            availableGods.add(godChoice.getGodName());
        }
        notify(this);
    }

    //imposta il worker scelto per il turno
    public void setWorkerChoice(WorkerChoice workerChoice) {
        //controllo se il lavoratore è disponibile
        if (currentPlayer.getWorkers()[workerChoice.getWorkerNumber()].getCanBeUsed()) {
            this.currentWorker = currentPlayer.getWorkers()[workerChoice.getWorkerNumber()];
        } else {
            outcome = Outcome.UNAVAILABLE_WORKER;
        }
        notify(this);
    }

    public void setDirectionMove(DirectionChoice directionChoice) {
        Cell nextCell;
        nextCell = map.getNextWorkerCell(currentWorker.getCurrentWorkerCell(), directionChoice.getDirection());
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
        notify(this);

    }

    public void setDirectionBuild(DirectionChoice directionChoice) {
        Cell nextCell;

        nextCell = map.getNextWorkerCell(currentWorker.getCurrentWorkerCell(), directionChoice.getDirection());
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
        notify(this);
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

        for (Worker w : currentPlayer.getWorkers()) {
            w.setCanBeUsed(w.checkSurroundingCells());
        }
        notify(this);
    }

    public void parseIntChoice(IntChoice intChoice) {

    }

}