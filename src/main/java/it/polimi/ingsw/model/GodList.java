package it.polimi.ingsw.model;

public enum GodList {
    APOLLO,
    ARTEMIS,
    ATHENA,
    ATLAS,
    DEMETER,
    HEPHAESTUS,
    MINOTAUR,
    PAN,
    ZEUS,
    PROMETHEUS,
    TRITON,
    CHARON,
    CHRONUS,
    HESTIA;

    public static GodList parseInput(String input) {
        return Enum.valueOf(GodList.class, input.toUpperCase());
    }

    public Worker parseGod(GodList godList) {
        switch (this) {
            case APOLLO:

        }
    }
}
