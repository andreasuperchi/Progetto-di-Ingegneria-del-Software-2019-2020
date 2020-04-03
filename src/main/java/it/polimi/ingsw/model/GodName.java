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

    public Worker parseGod(Player player, Cell baseWorkerCell) {
        try {
            switch (this) {
                case APOLLO:
                    return new WorkerApollo(player, baseWorkerCell);
                case ARTEMIS:
                    return new WorkerArtemis(player, baseWorkerCell);
                case ATHENA:
                    return new WorkerAthena(player, baseWorkerCell);
                case ATLAS:
                    return new WorkerAtlas(player, baseWorkerCell);
                case CHARON:
                    return new WorkerCharon(player, baseWorkerCell);
                case CHRONUS:
                    return new WorkerChronus(player, baseWorkerCell);
                case DEMETER:
                    return new WorkerDemeter(player, baseWorkerCell);
                case HEPHAESTUS:
                    return new WorkerHephaestus(player, baseWorkerCell);
                case HESTIA:
                    return new WorkerHestia(player, baseWorkerCell);
                case MINOTAUR:
                    return new WorkerMinotaur(player, baseWorkerCell);
                case PAN:
                    return new WorkerPan(player, baseWorkerCell);
                case PROMETHEUS:
                    return new WorkerPrometheus(player, baseWorkerCell);
                case TRITON:
                    return new WorkerTriton(player, baseWorkerCell);
                case ZEUS:
                    return new WorkerZeus(player, baseWorkerCell);
                default:
                    throw new IllegalArgumentException();
            }
        } catch (IllegalArgumentException e) {
            System.out.println("The God does not exist!");
            return null;
        }
    }
}
