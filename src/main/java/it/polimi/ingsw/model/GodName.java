package it.polimi.ingsw.model;

import it.polimi.ingsw.model.specialworkers.*;

public enum GodName {
    APOLLO,
    ARTEMIS,
    ATHENA,
    ATLAS,
    CHARON,
    CHRONUS,
    DEMETER,
    HEPHAESTUS,
    HESTIA,
    MINOTAUR,
    PAN,
    PROMETHEUS,
    TRITON,
    ZEUS,

    SELECTED_GOD;


    public static GodName parseInput(String input) {
        return Enum.valueOf(GodName.class, input.toUpperCase());
    }

    public Worker parseGod() {
        switch (this) {
            case APOLLO:
                return new WorkerApollo();
            case ARTEMIS:
                return new WorkerArtemis();
            case ATHENA:
                return new WorkerAthena();
            case ATLAS:
                return new WorkerAtlas();
            case CHARON:
                return new WorkerCharon();
            case CHRONUS:
                return new WorkerChronus();
            case DEMETER:
                return new WorkerDemeter();
            case HEPHAESTUS:
                return new WorkerHephaestus();
            case HESTIA:
                return new WorkerHestia();
            case MINOTAUR:
                return new WorkerMinotaur();
            case PAN:
                return new WorkerPan();
            case PROMETHEUS:
                return new WorkerPrometheus();
            case TRITON:
                return new WorkerTriton();
            case ZEUS:
                return new WorkerZeus();
            default:
                throw new IllegalArgumentException("Unexpected God!");
        }
    }
}
