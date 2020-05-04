package it.polimi.ingsw.model;

public class Player {

    private String name;
    private int age;
    private boolean isInGame;
    private Worker[] workers;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        isInGame = true;
        workers = new Worker[2];
        workers[0] = new Worker();
        workers[1] = new Worker();
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

    public void setWorkers(GodName godName) {
        workers[0] = godName.parseGod();
        workers[1] = godName.parseGod();
    }
}
