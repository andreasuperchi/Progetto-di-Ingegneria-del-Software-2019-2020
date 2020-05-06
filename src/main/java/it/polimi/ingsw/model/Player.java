package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Player {

    private String name;
    private int age;
    private boolean isInGame;
    private Worker[] workers;
    private String symbol;

    public Player(String name, int age, String symbol) {
        this.name = name;
        this.age = age;
        this.isInGame = true;
        this.symbol = symbol;
        workers = new Worker[2];
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsInGame() {
        return isInGame;
    }

    public void setIsInGame(boolean isInGame) {
        this.isInGame = isInGame;
    }

    public boolean getInGame() {
        return isInGame;
    }

    public void setInGame(boolean inGame) {
        isInGame = inGame;
    }

    public Worker[] getWorkers() {
        return workers;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setWorkers(GodName godName) {
        workers[0] = godName.parseGod();
        workers[1] = godName.parseGod();
        workers[0].symbol = symbol + "A";
        workers[1].symbol = symbol + "B";
    }
}
