package it.polimi.ingsw.model;

import java.util.ArrayList;

public class Player {

    private String name;
    private int age;
    private boolean isInGame;
    private Worker[] workers;
    private String symbol;

    /**
     * Builds a new player, instantiating his 2 workers
     *
     * @param name   is the player's name
     * @param age    is the player's age
     * @param symbol is the player's symbol, through which the workers of this player are going to be represented on the board
     */
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

    /**
     * Initializes the 2 workers of the player to the one specified
     *
     * @param godName is the actual God that the player chose for the game
     */
    public void setWorkers(GodName godName) {
        workers[0] = godName.parseGod();
        workers[1] = godName.parseGod();
        workers[0].symbol = symbol + "A\u001b[0m";
        workers[1].symbol = symbol + "B\u001b[0m";
    }
}
