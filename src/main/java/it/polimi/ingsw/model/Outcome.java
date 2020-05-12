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
    INVALID_WORKER,
    INVALID_ACTION,
    OUT_OF_MAP,
    NOT_MOVED_ERROR,
    INVALID_CELL,
    WORKERS_PLACEMENT_MENU,
    NO_SPECIAL_POWER,
    CANT_GO_TO_END_TURN,
    USED_SPECIAL_POWER,
    CONFIRM_END_TURN;

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
                        "\t[1]Worker A" +
                        "\t[2]Worker B");
                break;
            case AVAILABLE_GODS_MENU:
                out = new StringBuilder("Pick some Gods for the Game:\n" +
                        "\t[1]APOLLO" +
                        "\t[2]ARTEMIS" +
                        "\t[3]ATHENA" +
                        "\t[4]ATLAS" +
                        "\t[5]CHARON" +
                        "\t[6]CHRONUS" +
                        "\t[7]DEMETER" +
                        "\t[8]HEPHAESTUS" +
                        "\t[9]HESTIA" +
                        "\t[10]MINOTAUR" +
                        "\t[11]PAN" +
                        "\t[12]PROMETHEUS" +
                        "\t[13]TRITON" +
                        "\t[14]ZEUS");
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
                out = new StringBuilder("your god has not special powers, do another action.");
                break;
            case USED_SPECIAL_POWER:
                out = new StringBuilder("The Special Power has already been used");
                break;
            case CONFIRM_END_TURN:
                out = new StringBuilder("Are you sure you want to end your turn?\n" +
                        "\t[1]Yes" +
                        "\t[2]No");
                break;
            default:
                throw new IllegalArgumentException();
        }

        return out.toString();
    }


}
