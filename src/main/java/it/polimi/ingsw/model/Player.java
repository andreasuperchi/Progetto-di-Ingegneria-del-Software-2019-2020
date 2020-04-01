package it.polimi.ingsw.model;

public class Player {

    private String name;
    private int age;
    private boolean isInGame;
    private String god;
    private Worker[] workers;

    public Player(String name, int age) {
        this.name = name;
        this.age = age;
        isInGame = true;
        god = null;
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

    public String getGod() {
        return god;
    }

    public void setGod(String god) {
        this.god = god;
    }

    public boolean getInGame() { return isInGame; }

    public void setInGame(boolean inGame) { isInGame = inGame; }

    public Worker[] getWorkers() { return workers; }

    public void setWorkers(Worker[] workers) { this.workers = workers; }

    public void chooseGod(String godName) {

    }


}
