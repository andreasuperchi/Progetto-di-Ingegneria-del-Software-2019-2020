package it.polimi.ingsw.model;

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
    INVALID_NUMBER_OF_PLAYERS,
    INVALID_WORKER,
    INVALID_ACTION,
    OUT_OF_MAP,
    NOT_MOVED_ERROR,
    INVALID_CELL,
    WORKERS_PLACEMENT_MENU,
    NO_SPECIAL_POWER,
    CANT_GO_TO_END_TURN;

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
                        "\t[0]Worker A" +
                        "\t[1]Worker B");
                break;
            case AVAILABLE_GODS_MENU:
                out = new StringBuilder("Pick some Gods for the Game:\n" +
                        "\t[0]Apollo" +
                        "\t[1]ARTEMIS" +
                        "\t[2]ATHENA" +
                        "\t[3]ATLAS" +
                        "\t[4]CHARON" +
                        "\t[5]CHRONUS" +
                        "\t[6]DEMETER" +
                        "\t[7]HEPHAESTUS" +
                        "\t[8]HESTIA" +
                        "\t[9]MINOTAUR" +
                        "\t[10]PAN" +
                        "\t[11]PROMETHEUS" +
                        "\t[12]TRITON" +
                        "\t[13]ZEUS");
                break;
            case GOD_CHOICE_MENU:
                int index = 0;
                out = new StringBuilder("Select One God:\n");
                for (GodName g : Model.getAvailableGods()) {
                    out.append("\t[").append(index).append("]").append(g);
                    index++;
                }
                break;
            case ACTION_MENU:
                out = new StringBuilder("Choose your action:\n" +
                        "\t[0]Move" +
                        "\t[1]Build" +
                        "\t[2]Additional Power" +
                        "\t[3]End Turn");
                break;
            case DIRECTION_MENU:
                out = new StringBuilder("Choose a Direction:\n" +
                        "\t[0]NORTH" +
                        "\t[1]NORTH_EAST" +
                        "\t[2]EAST" +
                        "\t[3]SOUTH_EAST" +
                        "\t[4]SOUTH" +
                        "\t[5]SOUTH_WEST" +
                        "\t[6]WEST" +
                        "\t[7]NORTH_WEST");
                break;
            case INVALID_PLAYER:
                out = new StringBuilder("It's Not Your Turn!");
                break;
            case INVALID_NUMBER_OF_PLAYERS:
                out = new StringBuilder("Invalid Number of Players!");
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
                out = new StringBuilder("your god has not special powers, do another action.");
            default:
                throw new IllegalArgumentException();
        }

        return out.toString();
    }


}
