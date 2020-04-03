package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Observable;

public class Model extends Observable {
    private ArrayList<GodName> availableGods;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Worker currentWorker;
    private boolean gameOver;
    private Map map;

    public Model(ArrayList<Player> players, Player currentPlayer, Worker currentWorker, boolean gameOver) {
        this.players = players;
        this.currentPlayer = currentPlayer;
        this.currentWorker = currentWorker;
        this.gameOver = gameOver;
        this.map = new Map();
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

    public Map getMap() {
        return map;
    }

    public void setMap(Map map) {
        this.map = map;
    }

    public void addPlayer(Player player) {
        players.add(player);
        setChanged();
        notifyObservers();
    }

    public void setAvailableGods(ArrayList<GodName> gods) {
        this.availableGods.addAll(gods);
        setChanged();
        notifyObservers();
    }
}
