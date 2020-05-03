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
    AVAILABLE_GODS_MENU;

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
                out = "You can't use this god!";
                break;
            case DUPLICATE_GOD:
                out = "This god has already been selected!";
                break;
            case UNAVAILABLE_WORKER:
                out = "Selected worker is unavailable!";
                break;
            case WORKER_MENU:
                out = "Choose your Worker: \n[0]Worker 1 \n[1]Worker 2";
                break;
            case AVAILABLE_GODS_MENU:
                out = "Pick some Gods for the Game: [0]Apollo \n[1]ARTEMIS \n[2]ATHENA \n[3]ATLAS \n[4]CHARON \n[5]CHRONUS \n[6]DEMETER \n[7]HEPHAESTUS \n[8]HESTIA \n[9]MINOTAUR \n[10]PAN \n[11]PROMETHEUS \n[12]TRITON \n[13]ZEUS";
            default:
                throw new IllegalArgumentException();
        }

        return out;
    }
}
