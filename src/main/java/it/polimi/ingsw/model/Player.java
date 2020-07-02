package it.polimi.ingsw.model;

public class Player {

    private final String name;
    private final int age;
    private boolean isInGame;
    private final Worker[] workers;
    private final String symbol;

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

    public boolean getIsInGame() {
        return isInGame;
    }

    public void setIsInGame(boolean isInGame) {
        this.isInGame = isInGame;
        if (!isInGame) {
            workers[0].symbol = "";
            workers[0].getCurrentWorkerCell().setIsOccupied(false);
            workers[1].symbol = "";
            workers[1].getCurrentWorkerCell().setIsOccupied(false);
        }
    }

    public Worker[] getWorkers() {
        return workers;
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
