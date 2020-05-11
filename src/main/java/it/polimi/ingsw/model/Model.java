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

    enum turnPhase {
        AVAILABLE_GODS, GOD_CHOICE, WORKER_PLACEMENT,
        WORKER_CHOICE, ACTION_CHOICE, MOVE, BUILD, SPECIAL_POWER, END_TURN
    }

    private turnPhase currentPhase;
    private Outcome outcome;

    public Model(ArrayList<Player> players, int numberOfPlayers) {
        availableGods = new ArrayList<GodName>();
        this.players = new ArrayList<>();
        this.players.addAll(players);
        this.numberOfPlayers = numberOfPlayers;
        gameOver = false;
        currentPhase = turnPhase.AVAILABLE_GODS;
        map = new Map();
        outcome = Outcome.AVAILABLE_GODS_MENU;
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

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public turnPhase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(turnPhase currentPhase) {
        this.currentPhase = currentPhase;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void doAction(IntChoice intChoice) {
        if (!currentPlayer.equals(intChoice.getPlayer())) {
            outcome = Outcome.INVALID_PLAYER;
        } else {
            switch (currentPhase) {
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
                        if (currentPlayer.getWorkers()[0].getCurrentWorkerCell() == null) {
                            currentPlayer.getWorkers()[0].setCurrentWorkerCell(workerCell);
                        } else {
                            currentPlayer.getWorkers()[1].setCurrentWorkerCell(workerCell);
                            updateCurrentPlayer();
                        }
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.INVALID_INPUT;
                    }
                    //controllo che tutti i giocatori abbiano piazzato i loro workers
                    for (Player p : players) {
                        for (Worker w : p.getWorkers()) {
                            if (w.getCurrentWorkerCell() != null) {
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
                    if (intChoice.getValue() == 0 || intChoice.getValue() == 1) {
                        if (!currentPlayer.getWorkers()[intChoice.getValue()].canBeUsed) {
                            outcome = Outcome.UNAVAILABLE_WORKER;
                        } else {
                            currentWorker = currentPlayer.getWorkers()[intChoice.getValue()];
                            currentPhase = turnPhase.ACTION_CHOICE;
                            outcome = Outcome.ACTION_MENU;
                        }

                    } else {
                        outcome = Outcome.INVALID_WORKER;
                    }
                    break;

                case ACTION_CHOICE:
                    if (intChoice.getValue() < 0 || intChoice.getValue() > 3) {
                        outcome = Outcome.INVALID_ACTION;
                    } else {
                        try {
                            processAction(intChoice.getValue());
                        } catch (IllegalArgumentException e) {
                            outcome = Outcome.INVALID_INPUT;
                        }
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
                    if (intChoice.getValue() == 0 || intChoice.getValue() == 1) {
                        if (intChoice.getValue() == 0) {
                            endTurn();
                            currentPhase = turnPhase.WORKER_CHOICE;
                            outcome = Outcome.WORKER_MENU;
                        } else {
                            currentPhase = turnPhase.ACTION_CHOICE;
                            outcome = Outcome.ACTION_MENU;
                        }
                    } else {
                        outcome = Outcome.INVALID_INPUT;
                    }
                    //TODO: gestire se il giocatore è fuori partita
                    //if (!currentPlayer.getInGame()){
                    //
                    //}
                    break;

            }
        }
        notify(this);
    }


    private void addGod(int index) {
        switch (index) {
            case 0:
                if (!availableGods.contains(GodName.APOLLO)) {
                    availableGods.add(GodName.APOLLO);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 1:
                if (!availableGods.contains(GodName.ARTEMIS)) {
                    availableGods.add(GodName.ARTEMIS);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 2:
                if (!availableGods.contains(GodName.ATHENA)) {
                    availableGods.add(GodName.ATHENA);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 3:
                if (!availableGods.contains(GodName.ATLAS)) {
                    availableGods.add(GodName.ATLAS);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 4:
                if (!availableGods.contains(GodName.CHARON)) {
                    availableGods.add(GodName.CHARON);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 5:
                if (!availableGods.contains(GodName.CHRONUS)) {
                    availableGods.add(GodName.CHRONUS);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 6:
                if (!availableGods.contains(GodName.DEMETER)) {
                    availableGods.add(GodName.DEMETER);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 7:
                if (!availableGods.contains(GodName.HEPHAESTUS)) {
                    availableGods.add(GodName.HEPHAESTUS);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 8:
                if (!availableGods.contains(GodName.HESTIA)) {
                    availableGods.add(GodName.HESTIA);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 9:
                if (!availableGods.contains(GodName.MINOTAUR)) {
                    availableGods.add(GodName.MINOTAUR);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 10:
                if (!availableGods.contains(GodName.PAN)) {
                    availableGods.add(GodName.PAN);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 11:
                if (!availableGods.contains(GodName.PROMETHEUS)) {
                    availableGods.add(GodName.PROMETHEUS);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 12:
                if (!availableGods.contains(GodName.TRITON)) {
                    availableGods.add(GodName.TRITON);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 13:
                if (!availableGods.contains(GodName.ZEUS)) {
                    availableGods.add(GodName.ZEUS);
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private void processAction(int input) {
        switch (input) {
            case 0:
                if (currentWorker.getHasMoved()) {
                    throw new IllegalArgumentException();
                } else {
                    currentPhase = turnPhase.MOVE;
                    outcome = Outcome.DIRECTION_MENU;
                }
                break;
            case 1:
                if (currentWorker.getHasMoved() && !currentWorker.getHasBuilt()) {
                    currentPhase = turnPhase.BUILD;
                    outcome = Outcome.DIRECTION_MENU;
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 2:
                if (!currentWorker.hasSpecialPower) {
                    outcome = Outcome.NO_SPECIAL_POWER;
                } else {
                    if (currentWorker.hasUsedSpecialPower) {
                        outcome = Outcome.USED_SPECIAL_POWER;
                    } else {
                        currentPhase = turnPhase.SPECIAL_POWER;
                        outcome = Outcome.DIRECTION_MENU;
                    }
                }
                break;
            case 3:
                if (currentWorker.hasMoved && currentWorker.hasBuilt) {
                    currentPhase = turnPhase.END_TURN;
                    outcome = Outcome.CONFIRM_END_TURN;
                } else {
                    throw new IllegalArgumentException();
                }
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
                return map.getGrid()[0][0];
            case 1:
                return map.getGrid()[0][1];
            case 2:
                return map.getGrid()[0][2];
            case 3:
                return map.getGrid()[0][3];
            case 4:
                return map.getGrid()[0][4];
            case 5:
                return map.getGrid()[1][0];
            case 6:
                return map.getGrid()[1][1];
            case 7:
                return map.getGrid()[1][2];
            case 8:
                return map.getGrid()[1][3];
            case 9:
                return map.getGrid()[1][4];
            case 10:
                return map.getGrid()[2][0];
            case 11:
                return map.getGrid()[2][1];
            case 12:
                return map.getGrid()[2][2];
            case 13:
                return map.getGrid()[2][3];
            case 14:
                return map.getGrid()[2][4];
            case 15:
                return map.getGrid()[3][0];
            case 16:
                return map.getGrid()[3][1];
            case 17:
                return map.getGrid()[3][2];
            case 18:
                return map.getGrid()[3][3];
            case 19:
                return map.getGrid()[3][4];
            case 20:
                return map.getGrid()[4][0];
            case 21:
                return map.getGrid()[4][1];
            case 22:
                return map.getGrid()[4][2];
            case 23:
                return map.getGrid()[4][3];
            case 24:
                return map.getGrid()[4][4];
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
        if (!currentPhase.equals(turnPhase.GOD_CHOICE)) {
            if (!currentPlayer.getWorkers()[0].canBeUsed && !currentPlayer.getWorkers()[1].canBeUsed) {
                currentPlayer.setInGame(false);
            }
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


