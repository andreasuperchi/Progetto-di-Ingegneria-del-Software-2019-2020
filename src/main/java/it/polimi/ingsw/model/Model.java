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

    private enum turnPhase {
        NUMBER_OF_PLAYERS, AVAILABLE_GODS, GOD_CHOICE, WORKER_PLACEMENT,
        WORKER_CHOICE, ACTION_CHOICE, MOVE, BUILD, SPECIAL_POWER, END_TURN
    }

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
                            currentPhase = turnPhase.WORKER_PLACEMENT;
                            outcome = Outcome.WORKERS_PLACEMENT_MENU;
                        } else {
                            outcome = Outcome.GOD_CHOICE_MENU;
                        }
                        updateCurrentPlayer();
                    }
                    break;

                case WORKER_PLACEMENT:
                    Cell workerCell;
                    int count = 0;

                    try {
                        workerCell = parseCell(intChoice.getValue());
                        if (currentPlayer.getWorkers()[0].getCurrentWorkerCell().equals(null)) {
                            currentPlayer.getWorkers()[0].setCurrentWorkerCell(workerCell);
                        } else if (currentPlayer.getWorkers()[1].getCurrentWorkerCell().equals(null)) {
                            currentPlayer.getWorkers()[1].setCurrentWorkerCell(workerCell);
                        } else {
                            updateCurrentPlayer();
                        }
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.INVALID_INPUT;
                    }
                    //controllo che tutti i giocatori abbiano piazzato i loro workers
                    for (Player p : players) {
                        for (Worker w : p.getWorkers()) {
                            if (!w.getCurrentWorkerCell().equals(null)) {
                                count++;
                            }
                        }
                    }
                    if (count == 6) {
                        currentPhase = turnPhase.WORKER_CHOICE;
                        outcome = Outcome.WORKER_MENU;
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

                case MOVE:
                    Direction direction;
                    Cell cell;

                    try {
                        direction = parseDirection(intChoice.getValue());
                        cell = map.getNextWorkerCell(currentWorker.currentWorkerCell, direction);
                        currentWorker.move(cell);
                        gameOver = currentWorker.winCondition();
                        if (gameOver) {
                            break;
                        }
                        currentPhase = turnPhase.ACTION_CHOICE;
                        outcome = Outcome.ACTION_MENU;
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.INVALID_INPUT;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        outcome = Outcome.OUT_OF_MAP;
                    }
                    break;

                case BUILD:
                    if (!currentWorker.hasMoved) {
                        outcome = Outcome.NOT_MOVED_ERROR;
                    } else {
                        try {
                            direction = parseDirection(intChoice.getValue());
                            cell = map.getNextWorkerCell(currentWorker.currentWorkerCell, direction);
                            gameOver = currentWorker.winCondition();
                            currentWorker.build(cell);
                            if (gameOver) {
                                break;
                            }
                            currentPhase = turnPhase.ACTION_CHOICE;
                            outcome = Outcome.ACTION_MENU;
                        } catch (IllegalArgumentException e) {
                            outcome = Outcome.INVALID_INPUT;
                        } catch (ArrayIndexOutOfBoundsException e) {
                            outcome = Outcome.OUT_OF_MAP;
                        }
                    }
                    break;

                case SPECIAL_POWER:
                    try {
                        direction = parseDirection(intChoice.getValue());
                        cell = map.getNextWorkerCell(currentWorker.currentWorkerCell, direction);
                        currentWorker.specialPower(cell);
                        gameOver = currentWorker.winCondition();
                        if (gameOver) {
                            break;
                        }
                        currentPhase = turnPhase.ACTION_CHOICE;
                        outcome = Outcome.ACTION_MENU;
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.INVALID_INPUT;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        outcome = Outcome.OUT_OF_MAP;
                    }

                case END_TURN:
                    if (!currentWorker.hasMoved || !currentWorker.hasBuilt) {
                        outcome = Outcome.CANT_GO_TO_END_TURN;
                    } else {
                        endTurn();
                        //TODO: gestire se il giocatore è fuori partita
                        //if (!currentPlayer.getInGame()){
                        //
                        //}
                        currentPhase = turnPhase.WORKER_CHOICE;
                        outcome = Outcome.WORKER_MENU;
                    }
                    break;

            }
        }
        notify(this);
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
                if (!currentWorker.hasSpecialPower) {
                    outcome = Outcome.NO_SPECIAL_POWER;
                } else {
                    currentPhase = turnPhase.SPECIAL_POWER;
                    outcome = Outcome.DIRECTION_MENU;
                }
                break;
            case 3:
                currentPhase = turnPhase.END_TURN;
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Direction parseDirection(int input) {
        switch (input) {
            case 0:
                return Direction.NORTH;
            case 1:
                return Direction.NORTH_EAST;
            case 2:
                return Direction.EAST;
            case 3:
                return Direction.SOUTH_EAST;
            case 4:
                return Direction.SOUTH;
            case 5:
                return Direction.SOUTH_WEST;
            case 6:
                return Direction.WEST;
            case 7:
                return Direction.NORTH_WEST;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Cell parseCell(int cellNumber) {
        switch (cellNumber) {
            case 0:
                return map.grid[0][0];
            case 1:
                return map.grid[0][1];
            case 2:
                return map.grid[0][2];
            case 3:
                return map.grid[0][3];
            case 4:
                return map.grid[0][4];
            case 5:
                return map.grid[1][0];
            case 6:
                return map.grid[1][1];
            case 7:
                return map.grid[1][2];
            case 8:
                return map.grid[1][3];
            case 9:
                return map.grid[1][4];
            case 10:
                return map.grid[2][0];
            case 11:
                return map.grid[2][1];
            case 12:
                return map.grid[2][2];
            case 13:
                return map.grid[2][3];
            case 14:
                return map.grid[2][4];
            case 15:
                return map.grid[3][0];
            case 16:
                return map.grid[3][1];
            case 17:
                return map.grid[3][2];
            case 18:
                return map.grid[3][3];
            case 19:
                return map.grid[3][4];
            case 20:
                return map.grid[4][0];
            case 21:
                return map.grid[4][1];
            case 22:
                return map.grid[4][2];
            case 23:
                return map.grid[4][3];
            case 24:
                return map.grid[4][4];
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

        if (!currentPlayer.getWorkers()[0].canBeUsed && !currentPlayer.getWorkers()[0].canBeUsed) {
            currentPlayer.setInGame(false);
        }
    }

    private void endTurn() {
        currentWorker.setHasBuilt(false);
        currentWorker.setHasMoved(false);

        updateCurrentPlayer();

        for (Worker w : currentPlayer.getWorkers()) {
            w.setCanBeUsed(w.checkSurroundingCells());
        }
    }


}


