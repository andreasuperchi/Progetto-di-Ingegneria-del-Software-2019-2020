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
    ZEUS;

    public static GodName parseInput(String input) {
        return Enum.valueOf(GodName.class, input.toUpperCase());
    }

    public Worker parseGod(Player player) {
        try {
            switch (this) {
                case APOLLO:
                    return new WorkerApollo(player);
                case ARTEMIS:
                    return new WorkerArtemis(player);
                case ATHENA:
                    return new WorkerAthena(player);
                case ATLAS:
                    return new WorkerAtlas(player);
                case CHARON:
                    return new WorkerCharon(player);
                case CHRONUS:
                    return new WorkerChronus(player);
                case DEMETER:
                    return new WorkerDemeter(player);
                case HEPHAESTUS:
                    return new WorkerHephaestus(player);
                case HESTIA:
                    return new WorkerHestia(player);
                case MINOTAUR:
                    return new WorkerMinotaur(player);
                case PAN:
                    return new WorkerPan(player);
                case PROMETHEUS:
                    return new WorkerPrometheus(player);
                case TRITON:
                    return new WorkerTriton(player);
                case ZEUS:
                    return new WorkerZeus(player);
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("The God does not exist!");
            return null;
        }
    }
}
