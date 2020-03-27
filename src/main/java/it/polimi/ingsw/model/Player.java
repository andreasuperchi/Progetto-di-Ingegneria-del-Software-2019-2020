package it.polimi.ingsw.model;

public class Player {

    private String name;
    private int age;
    private String color;
    private boolean isInGame;
    private String god;

    public Player(String name, int age, String color) {
        this.name = name;
        this.age = age;
        this.color = color;
        isInGame = true;
        god = null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public void chooseGod(String godName) {
        
    }


}
