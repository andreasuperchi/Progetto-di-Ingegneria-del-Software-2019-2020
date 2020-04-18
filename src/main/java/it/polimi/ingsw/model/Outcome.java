package it.polimi.ingsw.model;

public enum Outcome {
    WIN,
    LOSE,
    INVALID_DIRECTION,
    MOVE_COMPLETED,
    BUILD_COMPLETED,
    MOVE_AGAIN,
    BUILD_AGAIN,
    INVALID_GOD;

    public String printOutcome() {
        String out;

        switch (this) {
            case WIN:
                out = "You Win!";
                break;
            case LOSE:
                out = "You lose!";
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
            default:
                throw new IllegalArgumentException();
        }

        return out;
    }
}
