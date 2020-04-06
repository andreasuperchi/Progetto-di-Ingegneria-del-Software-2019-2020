package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
    private ArrayList<GodName> availableGods;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Worker currentWorker;
    private boolean gameOver;
    private static Map map;

    public Model(boolean gameOver) {
        this.gameOver = gameOver;
        map = new Map();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.players = new ArrayList<Player>(numberOfPlayers);
        setChanged();
        notifyObservers();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Worker getCurrentWorker() {
        return currentWorker;
    }

    public void setCurrentWorker(Worker currentWorker) {
        this.currentWorker = currentWorker;
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public static Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        Model.map = map;
    }

    public void addPlayer(Player player) {
        if (players.isEmpty()) {
            this.currentPlayer = player;
        }
        players.add(player);
        setChanged();
        notifyObservers();

    }

    public void setAvailableGods(ArrayList<GodName> gods) {
        this.availableGods.addAll(gods);
        setChanged();
        notifyObservers();
    }

    public void setPlayerWorker(GodName godName) {
        currentPlayer.getWorkers()[0] = godName.parseGod(currentPlayer);
        currentPlayer.getWorkers()[1] = godName.parseGod(currentPlayer);
        setChanged();
        notifyObservers();
    }

    public void setWorkerStartPosition(Cell[] arg) {
        currentPlayer.getWorkers()[0].setCurrentWorkerCell(arg[0]);
        currentPlayer.getWorkers()[1].setCurrentWorkerCell(arg[1]);
        setChanged();
        notifyObservers();
    }


}
