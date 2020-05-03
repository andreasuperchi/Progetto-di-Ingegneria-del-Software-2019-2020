package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.*;
import it.polimi.ingsw.view.Observable;

import java.util.ArrayList;

public class Model extends Observable<Model> implements Cloneable {
    private int numberOfPlayers;
    private static ArrayList<GodName> availableGods;
    private ArrayList<Player> players;
    private static Player currentPlayer;
    private Worker currentWorker;
    private boolean gameOver;
    private static Map map;

    private enum turnPhase {NUMBER_OF_PLAYERS, AVAILABLE_GODS, GOD_CHOICE, WORKER_CHOICE, ACTION_CHOICE, MOVE, BUILD, SPECIAL_POWER, END_TURN}

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

    public static ArrayList<GodName> getAvailableGods() {
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


    public void doAction(IntChoice intChoice) {
        if (!currentPlayer.equals(intChoice.getPlayer())) {
            outcome = Outcome.INVALID_PLAYER;
        } else {
            switch (currentPhase) {
                case NUMBER_OF_PLAYERS:
                    if (intChoice.getValue() != 2 || intChoice.getValue() != 3) {
                        outcome = Outcome.INVALID_NUMBER_OF_PLAYERS;
                    } else {
                        numberOfPlayers = intChoice.getValue();
                        currentPhase = turnPhase.AVAILABLE_GODS;
                        outcome = Outcome.AVAILABLE_GODS_MENU;
                    }
                    break;
                case AVAILABLE_GODS:
                    try {
                        addGod(intChoice.getValue());
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.INVALID_GOD;
                    }
                    if (availableGods.size() == numberOfPlayers) {
                        currentPhase = turnPhase.GOD_CHOICE;
                        outcome = Outcome.GOD_CHOICE_MENU;
                        updateCurrentPlayer();
                    }
                    break;
                case GOD_CHOICE:
                    if (intChoice.getValue() >= availableGods.size()) {
                        outcome = Outcome.INVALID_GOD;
                    } else {
                        GodName godName = availableGods.get(intChoice.getValue());
                        currentPlayer.setWorkers(godName);
                        availableGods.remove(godName);
                        if (availableGods.size() == 0) {
                            currentPhase = turnPhase.WORKER_CHOICE;
                            outcome = Outcome.WORKER_MENU;
                        } else {
                            outcome = Outcome.GOD_CHOICE_MENU;
                        }
                        updateCurrentPlayer();
                    }
                    break;
                case WORKER_CHOICE:
                    if (intChoice.getValue() != 0 || intChoice.getValue() != 1) {
                        outcome = Outcome.INVALID_WORKER;
                    } else {
                        if (!currentPlayer.getWorkers()[intChoice.getValue()].canBeUsed) {
                            outcome = Outcome.UNAVAILABLE_WORKER;
                        } else {
                            currentWorker = currentPlayer.getWorkers()[intChoice.getValue()];
                            currentPhase = turnPhase.ACTION_CHOICE;
                            outcome = Outcome.ACTION_MENU;
                        }
                    }
                    break;
                case ACTION_CHOICE:
                    if (intChoice.getValue() < 0 || intChoice.getValue() > 3) {
                        outcome = Outcome.INVALID_ACTION;
                    } else {
                        processAction(intChoice.getValue());
                    }
                    break;

            }


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

        updateCurrentPlayer();

        for (Worker w : currentPlayer.getWorkers()) {
            w.setCanBeUsed(w.checkSurroundingCells());
        }
        notify(this);
    }

    public void parseIntChoice(IntChoice intChoice) {

    }

    private void addGod(int index) {
        switch (index) {
            case 0:
                availableGods.add(GodName.APOLLO);
                break;
            case 1:
                availableGods.add(GodName.ARTEMIS);
                break;
            case 2:
                availableGods.add(GodName.ATHENA);
                break;
            case 3:
                availableGods.add(GodName.ATLAS);
                break;
            case 4:
                availableGods.add(GodName.CHARON);
                break;
            case 5:
                availableGods.add(GodName.CHRONUS);
                break;
            case 6:
                availableGods.add(GodName.DEMETER);
                break;
            case 7:
                availableGods.add(GodName.HEPHAESTUS);
                break;
            case 8:
                availableGods.add(GodName.HESTIA);
                break;
            case 9:
                availableGods.add(GodName.MINOTAUR);
                break;
            case 10:
                availableGods.add(GodName.PAN);
                break;
            case 11:
                availableGods.add(GodName.PROMETHEUS);
                break;
            case 12:
                availableGods.add(GodName.TRITON);
                break;
            case 13:
                availableGods.add(GodName.ZEUS);
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void processAction(int input) {
        switch (input) {
            case 0:
                currentPhase = turnPhase.MOVE;
                outcome = Outcome.DIRECTION_MENU;
                break;
            case 1:
                currentPhase = turnPhase.BUILD;
                outcome = Outcome.DIRECTION_MENU;
                break;
            case 2:
                currentPhase = turnPhase.SPECIAL_POWER;
                break;
            case 3:
                currentPhase = turnPhase.END_TURN;

                break;
            default:
                throw new IllegalArgumentException();

        }
    }

    private void updateCurrentPlayer() {
        if (players.indexOf(currentPlayer) == numberOfPlayers - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }
    }


}


