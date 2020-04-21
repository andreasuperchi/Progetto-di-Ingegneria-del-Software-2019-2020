package it.polimi.ingsw.model;

public enum Outcome {
    WIN,
    LOSE,
    INVALID_DIRECTION,
    MOVE_COMPLETED,
    BUILD_COMPLETED,
    MOVE_AGAIN,
    BUILD_AGAIN,
    INVALID_GOD,
    DUPLICATE_GOD,
    UNAVAILABLE_WORKER;

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
            case MOVE_COMPLETED:
                out = "You have successfully moved!";
                break;
            case BUILD_COMPLETED:
                out = "You have successfully built!";
                break;
            case MOVE_AGAIN:
                out = "Do you want to move again? Answer with y/n";
                break;
            case BUILD_AGAIN:
                out = "Do you want to build again? Answer with y/n";
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
            default:
                throw new IllegalArgumentException();
        }

        return out;
    }
}
