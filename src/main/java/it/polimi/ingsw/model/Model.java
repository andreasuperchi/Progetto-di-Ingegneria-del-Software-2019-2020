package it.polimi.ingsw.model;

import it.polimi.ingsw.model.choices.*;
import it.polimi.ingsw.view.Observable;

import java.util.ArrayList;
import java.util.Arrays;

public class Model extends Observable<Model> implements Cloneable {
    private static int numberOfPlayers;
    private static ArrayList<GodName> availableGods;
    private ArrayList<Player> players;
    private static Player currentPlayer;
    private Worker currentWorker;
    private boolean gameOver;
    private static Map map;
    private turnPhase currentPhase;
    private Outcome outcome;

    public enum turnPhase {
        AVAILABLE_GODS, GOD_CHOICE, WORKER_PLACEMENT,
        WORKER_CHOICE, ACTION_CHOICE, MOVE, BUILD, SPECIAL_POWER, END_TURN, GAME_OVER
    }

    /**
     * creates a new model object, initializing it with the players that are passed through the players array and with
     * the number of players that are passed too. Also sets the gameOver attribute to false, creates the map, sets the
     * initial phase and the initial outcome of the game and creates an arrayList "gods" which contains all
     * the gods implemented in this project
     *
     * @param players
     * @param numberOfPlayers
     */
    public Model(ArrayList<Player> players, int numberOfPlayers) {
        availableGods = new ArrayList<>();
        this.players = new ArrayList<>();
        this.players.addAll(players);
        Model.numberOfPlayers = numberOfPlayers;
        gameOver = false;
        currentPhase = turnPhase.AVAILABLE_GODS;
        map = new Map();
        outcome = Outcome.AVAILABLE_GODS_MENU;
        currentPlayer = players.get(0);
        Outcome.setGods(new ArrayList<>(Arrays.asList("Apollo", "Artemis", "Athena", "Atlas", "Charon", "Chronus", "Demeter", "Hephaestus",
                "Hestia", "Minotaur", "Pan", "Prometheus", "Triton")));
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

    public static int getNumberOfPlayers() {
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
                        outcome = Outcome.AVAILABLE_GODS_MENU;
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.AVAILABLE_GODS_ERROR;
                    }
                    if (availableGods.size() == numberOfPlayers) {
                        currentPhase = turnPhase.GOD_CHOICE;
                        outcome = Outcome.GOD_CHOICE_MENU;
                        updateCurrentPlayer();
                    }
                    break;

                case GOD_CHOICE:
                    if (intChoice.getValue() > availableGods.size()) {
                        outcome = Outcome.GOD_CHOICE_ERROR;
                    } else {
                        GodName godName = availableGods.get(intChoice.getValue() - 1);
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
                        outcome = Outcome.WORKERS_PLACEMENT_MENU;
                    } catch (IllegalArgumentException e) {
                        outcome = Outcome.WORKERS_PLACEMENT_ERROR;
                    }
                    //controllo che tutti i giocatori abbiano piazzato i loro workers
                    for (Player p : players) {
                        for (Worker w : p.getWorkers()) {
                            if (w.getCurrentWorkerCell() != null) {
                                count++;
                            }
                        }
                    }
                    if (count == 2 * numberOfPlayers) {
                        currentPhase = turnPhase.WORKER_CHOICE;
                        outcome = Outcome.WORKER_MENU;
                    }
                    break;

                case WORKER_CHOICE:
                    if (intChoice.getValue() == 1 || intChoice.getValue() == 2) {
                        if (!currentPlayer.getWorkers()[intChoice.getValue() - 1].canBeUsed) {
                            outcome = Outcome.UNAVAILABLE_WORKER;
                        } else {
                            currentWorker = currentPlayer.getWorkers()[intChoice.getValue() - 1];
                            currentPhase = turnPhase.ACTION_CHOICE;
                            outcome = Outcome.ACTION_MENU;
                        }

                    } else {
                        outcome = Outcome.WORKER_CHOICE_ERROR;
                    }
                    break;

                case ACTION_CHOICE:
                    if (intChoice.getValue() < 1 || intChoice.getValue() > 4) {
                        outcome = Outcome.ACTION_CHOICE_ERROR;
                    } else {
                        try {
                            processAction(intChoice.getValue());
                        } catch (IllegalArgumentException e) {
                            outcome = Outcome.PROCESS_ACTION_ERROR;
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
                        outcome = Outcome.DIRECTION_ERROR;
                        currentPhase = turnPhase.ACTION_CHOICE;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        outcome = Outcome.OUT_OF_MAP;
                        currentPhase = turnPhase.ACTION_CHOICE;
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
                        outcome = Outcome.DIRECTION_ERROR;
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
                        outcome = Outcome.DIRECTION_ERROR;
                        currentPhase = turnPhase.ACTION_CHOICE;
                    } catch (ArrayIndexOutOfBoundsException e) {
                        outcome = Outcome.OUT_OF_MAP;
                        currentPhase = turnPhase.ACTION_CHOICE;
                    }
                    break;

                case END_TURN:
                    if (intChoice.getValue() == 1 || intChoice.getValue() == 2) {
                        if (intChoice.getValue() == 1) {
                            endTurn();
                            if (!currentPlayer.getIsInGame()) {
                                int c = 0;

                                for (Player p : players) {
                                    if (p.getIsInGame()) {
                                        c++;
                                    }
                                }

                                if (c == 1) {
                                    gameOver = true;
                                } else {
                                    Player removedPlayer = currentPlayer;
                                    endTurn();
                                    players.remove(removedPlayer);
                                    currentPhase = turnPhase.WORKER_CHOICE;
                                    outcome = Outcome.WORKER_MENU;
                                }
                            } else {
                                currentPhase = turnPhase.WORKER_CHOICE;
                                outcome = Outcome.WORKER_MENU;
                            }

                        } else {
                            currentPhase = turnPhase.ACTION_CHOICE;
                            outcome = Outcome.ACTION_MENU;
                        }
                    } else {
                        outcome = Outcome.PROCESS_ACTION_ERROR;
                        currentPhase = turnPhase.ACTION_CHOICE;
                    }
                    break;

//                case GAME_OVER: //entro qui se il nuovo giocatore ha entrambi i worker murati
////                    Player removedPlayer = currentPlayer;
////                    endTurn();
////                    players.remove(removedPlayer);
////                    if (players.size() == 1) {
////                        gameOver = true;
////                        outcome = Outcome.WIN;
////                    } else {
////                        if (!currentPlayer.getInGame()) {
////                            outcome = Outcome.LOSE;
////                            currentPhase = turnPhase.GAME_OVER;
////                        } else {
////                            currentPhase = turnPhase.WORKER_CHOICE;
////                            outcome = Outcome.WORKER_MENU;
////                        }
////                    }
//
//
//                    break;
            }
        }
        notify(this);
    }


    private void addGod(int index) {
        if (index >= 0 && index <= Outcome.getGods().size()) {
            GodName selectedGod = GodName.parseInput(Outcome.getGods().get(index - 1));
            if (!availableGods.contains(selectedGod)) {
                availableGods.add(selectedGod);
                Outcome.getGods().remove(index - 1);
            }
        } else {
            throw new IllegalArgumentException();
        }
    }

    private void processAction(int input) {
        switch (input) {
            case 1:
                if (currentWorker.getHasMoved()) {
                    throw new IllegalArgumentException();
                } else {
                    currentPhase = turnPhase.MOVE;
                    outcome = Outcome.DIRECTION_MENU;
                }
                break;
            case 2:
                if (currentWorker.getHasMoved() && !currentWorker.getHasBuilt()) {
                    currentPhase = turnPhase.BUILD;
                    outcome = Outcome.DIRECTION_MENU;
                } else {
                    throw new IllegalArgumentException();
                }
                break;
            case 3:
                if (!currentWorker.hasSpecialPower) {
                    outcome = Outcome.NO_SPECIAL_POWER;
                    currentPhase = turnPhase.ACTION_CHOICE;
                } else if (currentWorker.canUseSpecialPower()) {
                    if (currentWorker.hasUsedSpecialPower) {
                        outcome = Outcome.USED_SPECIAL_POWER;
                        currentPhase = turnPhase.ACTION_CHOICE;
                    } else {
                        currentPhase = turnPhase.SPECIAL_POWER;
                        outcome = Outcome.DIRECTION_MENU;
                    }
                } else {
                    outcome = Outcome.CANT_USE_SPECIAL_POWER;
                    currentPhase = turnPhase.ACTION_CHOICE;
                }
                break;
            case 4:
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

    private void updateCurrentPlayer() {
        if (players.indexOf(currentPlayer) == numberOfPlayers - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(players.indexOf(currentPlayer) + 1);
        }
    }

    private void endTurn() {
        currentWorker.setHasBuilt(false);
        currentWorker.setHasMoved(false);
        currentWorker.setHasUsedSpecialPower(false);

        updateCurrentPlayer();

        for (Worker w : currentPlayer.getWorkers()) {
            w.setCanBeUsed(w.checkSurroundingCells());
        }

        currentPlayer.setIsInGame(currentPlayer.getWorkers()[0].canBeUsed || currentPlayer.getWorkers()[1].canBeUsed);
    }

    private Direction parseDirection(int input) {
        switch (input) {
            case 1:
                return Direction.NORTH;
            case 2:
                return Direction.NORTH_EAST;
            case 3:
                return Direction.EAST;
            case 4:
                return Direction.SOUTH_EAST;
            case 5:
                return Direction.SOUTH;
            case 6:
                return Direction.SOUTH_WEST;
            case 7:
                return Direction.WEST;
            case 8:
                return Direction.NORTH_WEST;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Cell parseCell(int cellNumber) {
        switch (cellNumber) {
            case 1:
                return map.getGrid()[0][0];
            case 2:
                return map.getGrid()[0][1];
            case 3:
                return map.getGrid()[0][2];
            case 4:
                return map.getGrid()[0][3];
            case 5:
                return map.getGrid()[0][4];
            case 6:
                return map.getGrid()[1][0];
            case 7:
                return map.getGrid()[1][1];
            case 8:
                return map.getGrid()[1][2];
            case 9:
                return map.getGrid()[1][3];
            case 10:
                return map.getGrid()[1][4];
            case 11:
                return map.getGrid()[2][0];
            case 12:
                return map.getGrid()[2][1];
            case 13:
                return map.getGrid()[2][2];
            case 14:
                return map.getGrid()[2][3];
            case 15:
                return map.getGrid()[2][4];
            case 16:
                return map.getGrid()[3][0];
            case 17:
                return map.getGrid()[3][1];
            case 18:
                return map.getGrid()[3][2];
            case 19:
                return map.getGrid()[3][3];
            case 20:
                return map.getGrid()[3][4];
            case 21:
                return map.getGrid()[4][0];
            case 22:
                return map.getGrid()[4][1];
            case 23:
                return map.getGrid()[4][2];
            case 24:
                return map.getGrid()[4][3];
            case 25:
                return map.getGrid()[4][4];
            default:
                throw new IllegalArgumentException();
        }
    }
}


