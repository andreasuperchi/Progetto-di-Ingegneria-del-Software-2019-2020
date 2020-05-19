package it.polimi.ingsw.model;

import java.util.ArrayList;
import java.util.Arrays;

public enum Outcome {
    WIN,
    LOSE,
    INVALID_INPUT,
    INVALID_GOD,
    DUPLICATE_GOD,
    UNAVAILABLE_WORKER,
    WORKER_MENU,
    ACTION_MENU,
    AVAILABLE_GODS_MENU,
    DIRECTION_MENU,
    GOD_CHOICE_MENU,
    INVALID_PLAYER,
    INVALID_WORKER,
    INVALID_ACTION,
    OUT_OF_MAP,
    NOT_MOVED_ERROR,
    INVALID_CELL,
    WORKERS_PLACEMENT_MENU,
    NO_SPECIAL_POWER,
    CANT_GO_TO_END_TURN,
    USED_SPECIAL_POWER,
    CONFIRM_END_TURN,
    CANT_USE_SPECIAL_POWER;

    private static ArrayList<String> gods;

    public static ArrayList<String> getGods() {
        return gods;
    }

    public static void setGods(ArrayList<String> gods) {
        Outcome.gods = gods;
    }

    public String printOutcome() {
        StringBuilder out;

        switch (this) {
            case WIN:
                out = new StringBuilder("You Win!");
                break;
            case LOSE:
                out = new StringBuilder("You Lose!");
                break;
            case INVALID_INPUT:
                out = new StringBuilder("Invalid input. Try again.");
                break;
            case INVALID_GOD:
                out = new StringBuilder("You can't select this god!");
                break;
            case DUPLICATE_GOD:
                out = new StringBuilder("This god has already been selected!");
                break;
            case UNAVAILABLE_WORKER:
                out = new StringBuilder("Selected worker is unavailable! Please choose another worker.");
                break;
            case WORKER_MENU:
                out = new StringBuilder("Choose your Worker:\n" +
                        "\t[1]Worker " + Model.getCurrentPlayer().getWorkers()[0].symbol +
                        "\t[2]Worker " + Model.getCurrentPlayer().getWorkers()[1].symbol);
                break;
            case AVAILABLE_GODS_MENU:
                out = new StringBuilder("Pick " + Model.getNumberOfPlayers() + " Gods for the Game:\n");
                for (String s : gods) {
                    out.append("\t[").append(gods.indexOf(s) + 1).append("]").append(s);
                    if (gods.indexOf(s) + 1 == 7) {
                        out.append("\n");
                    }
                }
                break;
            case GOD_CHOICE_MENU:
                int index = 1;
                out = new StringBuilder("Select One God:\n");
                for (GodName g : Model.getAvailableGods()) {
                    out.append("\t[").append(index).append("]").append(g);
                    index++;
                }
                break;
            case ACTION_MENU:
                out = new StringBuilder("Choose your action:\n" +
                        "\t[1]Move" +
                        "\t[2]Build" +
                        "\t[3]Additional Power" +
                        "\t[4]End Turn");
                break;
            case DIRECTION_MENU:
                out = new StringBuilder("Choose a Direction:\n" +
                        "\t[1]NORTH" +
                        "\t[2]NORTH_EAST" +
                        "\t[3]EAST" +
                        "\t[4]SOUTH_EAST" +
                        "\t[5]SOUTH" +
                        "\t[6]SOUTH_WEST" +
                        "\t[7]WEST" +
                        "\t[8]NORTH_WEST");
                break;
            case INVALID_PLAYER:
                out = new StringBuilder("It's Not Your Turn!");
                break;
            case INVALID_WORKER:
                out = new StringBuilder("Invalid Worker! Please Choose a Valid Worker.");
                break;
            case INVALID_ACTION:
                out = new StringBuilder("Invalid Action! Please Choose a valid Action.");
                break;
            case OUT_OF_MAP:
                out = new StringBuilder("You can't go out of the map! Please choose a new direction.");
                break;
            case NOT_MOVED_ERROR:
                out = new StringBuilder("You have to move before building!");
                break;
            case CANT_GO_TO_END_TURN:
                out = new StringBuilder("You have to move and build before ending your turn!");
                break;
            case INVALID_CELL:
                out = new StringBuilder("The cell you selected is not valid. Please choose another one.");
                break;
            case WORKERS_PLACEMENT_MENU:
                out = new StringBuilder("Enter the number of the cell where you want to place your worker.");
                break;
            case NO_SPECIAL_POWER:
                out = new StringBuilder("\u001b[31mYour god has no special power!\u001b[0m \nChoose your action: \n" +
                        "\t[1]Move" +
                        "\t[2]Build" +
                        "\t[3]Additional Power" +
                        "\t[4]End Turn");
                break;
            case USED_SPECIAL_POWER:
                out = new StringBuilder("The Special Power has already been used.");
                break;
            case CONFIRM_END_TURN:
                out = new StringBuilder("Are you sure you want to end your turn?\n" +
                        "\t[1]Yes" +
                        "\t[2]No");
                break;
            case CANT_USE_SPECIAL_POWER:
                out = new StringBuilder("\u001b[31mYou can't use your special power now.\u001b[0m" +
                        "\nChoose your action:\n" +
                        "\t[1]Move" +
                        "\t[2]Build" +
                        "\t[3]Additional Power" +
                        "\t[4]End Turn");
                break;
            default:
                throw new IllegalArgumentException();
        }

        return out.toString();
    }


}
