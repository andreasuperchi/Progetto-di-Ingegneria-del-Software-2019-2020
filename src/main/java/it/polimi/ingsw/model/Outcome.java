package it.polimi.ingsw.model;

import java.util.ArrayList;

public enum Outcome {
    WIN,
    LOSE,
    DIRECTION_ERROR,
    GOD_CHOICE_ERROR,
    UNAVAILABLE_WORKER,
    WORKER_MENU,
    ACTION_MENU,
    AVAILABLE_GODS_MENU,
    DIRECTION_MENU,
    GOD_CHOICE_MENU,
    INVALID_PLAYER,
    WORKER_CHOICE_ERROR,
    ACTION_CHOICE_ERROR,
    OUT_OF_MAP,
    NOT_MOVED_ERROR,
    INVALID_CELL,
    WORKERS_PLACEMENT_MENU,
    NO_SPECIAL_POWER,
    CANT_GO_TO_END_TURN,
    USED_SPECIAL_POWER,
    CONFIRM_END_TURN,
    CANT_USE_SPECIAL_POWER,
    PROCESS_ACTION_ERROR,
    WORKERS_PLACEMENT_ERROR,
    AVAILABLE_GODS_ERROR;

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
            case AVAILABLE_GODS_MENU:
                out = new StringBuilder("Pick " + Model.getNumberOfPlayers() + " Gods for the Game:\n");
                for (String s : gods) {
                    out.append("\t[").append(gods.indexOf(s) + 1).append("]").append(s);
                    if (gods.indexOf(s) + 1 == 7) {
                        out.append("\n");
                    }
                }
                break;
            case AVAILABLE_GODS_ERROR:
                out = new StringBuilder("\u001b[31mPlease choose a valid God from the list below.\u001b[0m\n");
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
            case GOD_CHOICE_ERROR:
                out = new StringBuilder("\u001b[31mChoose a valid God!\u001b[0m \nSelect One God:\n");
                index = 1;
                for (GodName g : Model.getAvailableGods()) {
                    out.append("\t[").append(index).append("]").append(g);
                    index++;
                }
                break;
            case WORKERS_PLACEMENT_MENU:
                out = new StringBuilder("Enter the number of the cell where you want to place your worker.");
                break;
            case WORKERS_PLACEMENT_ERROR:
                out = new StringBuilder("\u001b[31mPlease choose a valid cell!\u001b[0m\n");
                break;
            case WORKER_MENU:
                out = new StringBuilder("Choose your Worker:\n" +
                        "\t[1]Worker " + Model.getCurrentPlayer().getWorkers()[0].symbol +
                        "\t[2]Worker " + Model.getCurrentPlayer().getWorkers()[1].symbol);
                break;
            case WORKER_CHOICE_ERROR:
                out = new StringBuilder("\u001b[31mInvalid Worker! Please Choose a Valid Worker.\u001b[0m" +
                        "\nChoose your Worker:\n" +
                        "\t[1]Worker " + Model.getCurrentPlayer().getWorkers()[0].symbol +
                        "\t[2]Worker " + Model.getCurrentPlayer().getWorkers()[1].symbol);
                break;
            case ACTION_MENU:
                out = new StringBuilder("Choose your action:\n" +
                        "\t[1]Move" +
                        "\t[2]Build" +
                        "\t[3]Additional Power" +
                        "\t[4]End Turn");
                break;
            case ACTION_CHOICE_ERROR:
                out = new StringBuilder("\u001b[31mInvalid Action! Please Choose a valid Action.\u001b[0m" +
                        "\nChoose your action: \n" +
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
            case DIRECTION_ERROR:
                out = new StringBuilder("\u001b[31mInvalid direction. Try again.\u001b[0m \nChoose your action:\n" +
                        "\t[1]Move" +
                        "\t[2]Build" +
                        "\t[3]Additional Power" +
                        "\t[4]End Turn");
                break;
            case UNAVAILABLE_WORKER:
                out = new StringBuilder("\u001b[31mSelected worker is unavailable! Please choose another one.\u001b[0m \n" +
                        "\t[1]Worker " + Model.getCurrentPlayer().getWorkers()[0].symbol +
                        "\t[2]Worker " + Model.getCurrentPlayer().getWorkers()[1].symbol);
                break;
            case INVALID_PLAYER:
                out = new StringBuilder("It's Not Your Turn!");
                break;
            case OUT_OF_MAP:
                out = new StringBuilder("\u001b[31mYou can't go out of the map! Please choose a new direction.\u001b[0m \n" +
                        "\t[1]Move" +
                        "\t[2]Build" +
                        "\t[3]Additional Power" +
                        "\t[4]End Turn");
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
            case PROCESS_ACTION_ERROR:
                out = new StringBuilder("\u001b[31mInvalid choice. Try again.\u001b[0m" +
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
