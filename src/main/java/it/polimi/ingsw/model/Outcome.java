package it.polimi.ingsw.model;

public enum Outcome {
    WIN,
    LOSE,
    INVALID_DIRECTION,
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
    CANT_GO_TO_END_TURN;

    public String printOutcome() {
        String out;

        switch (this) {
            case WIN:
                out = "You Win!";
                break;
            case LOSE:
                out = "You Lose!";
                break;
            case INVALID_DIRECTION:
                out = "The direction you chose is invalid!";
                break;
            case INVALID_GOD:
                out = "You can't select this god!";
                break;
            case DUPLICATE_GOD:
                out = "This god has already been selected!";
                break;
            case UNAVAILABLE_WORKER:
                out = "Selected worker is unavailable! Please choose another worker";
                break;
            case WORKER_MENU:
                out = "Choose your Worker:\n" +
                        "[0]Worker A\n" +
                        "[1]Worker B\n";
                break;
            case AVAILABLE_GODS_MENU:
                out = "Pick some Gods for the Game:\n" +
                        "[0]Apollo\n" +
                        "[1]ARTEMIS\n" +
                        "[2]ATHENA\n" +
                        "[3]ATLAS\n" +
                        "[4]CHARON\n" +
                        "[5]CHRONUS\n" +
                        "[6]DEMETER\n" +
                        "[7]HEPHAESTUS\n" +
                        "[8]HESTIA\n" +
                        "[9]MINOTAUR\n" +
                        "[10]PAN\n" +
                        "[11]PROMETHEUS\n" +
                        "[12]TRITON\n" +
                        "[13]ZEUS\n";
                break;
            case GOD_CHOICE_MENU:
                int index = 0;
                out = "Select One God:\n";
                for (GodName g : Model.getAvailableGods()) {
                    out = out + "[" + index + "]" + g + "\n";
                }
                break;
            case ACTION_MENU:
                out = "Choose your action:\n" +
                        "[0]Move\n" +
                        "[1]Build\n" +
                        "[2]Additional Power\n" +
                        "[3]End Turn\n";
                break;
            case DIRECTION_MENU:
                out = "Choose a Direction:\n" +
                        "    [0]NORTH\n" +
                        "    [1]NORTH_EAST\n" +
                        "    [2]EAST\n" +
                        "    [3]SOUTH_EAST\n" +
                        "    [4]SOUTH\n" +
                        "    [5]SOUTH_WEST\n" +
                        "    [6]WEST\n" +
                        "    [7]NORTH_WEST\n";
                break;
            case INVALID_PLAYER:
                out = "It's Not Your Turn";
                break;
            case INVALID_NUMBER_OF_PLAYERS:
                out = "Invalid Number of Players ";
                break;
            case INVALID_WORKER:
                out = "Invalid Worker! Please Choose a Valid Worker";
                break;
            case INVALID_ACTION:
                out = "Invalid Action! Please Choose a valid Action";
                break;
            case OUT_OF_MAP:
                out = "You can't go out of the map! Please choose a new direction";
                break;
            case NOT_MOVED_ERROR:
                out = "You have to move before building!";
                break;
            case CANT_GO_TO_END_TURN:
                out = "You have to move and build before ending your turn!";
                break;
            default:
                throw new IllegalArgumentException();
        }

        return out;
    }

}
