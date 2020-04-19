package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Direction;
import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;

public class RemoteView extends View {
    private boolean setupDone = false;
    private int counter;

    protected RemoteView(Player player) {
        super(player);
    }

    private class StringReceiver implements Observer<String> {
        @Override
        public void update(String message) {
            if (!setupDone) {
                try {
                    GodName godName = GodName.parseInput(message);
                    counter--;
                    if (counter == 0) {
                        setupDone = true;
                    }
                    processGodChoice(godName);
                } catch (IllegalArgumentException e) {
                    //TODO stampare lato client
                }
            } else {
                if (message.equals("y") || message.equals("n")) {
                    processStringChoice(message);
                } else {
                    try {
                        Direction direction = Direction.parseInput(message);
                        processDirectionPlayerChoice(direction);
                    } catch (IllegalArgumentException e) {
                        //TODO stampare lato client
                    }
                }
            }

        }
    }

    private class IntReceiver implements Observer<Integer> {
        @Override
        public void update(Integer message) {
            if (!setupDone) {
                //ricevo il numero di giocatori della partita
                if (message <= 1 || message > 3) {
                    //TODO stampare lato client
                } else {
                    counter = 2 * message;
                    processNumberOfPlayersChoice(message);
                }
            } else {
                //stiamo scegliendo il numero del worker per giocare
                if (message != 0 && message != 1) {
                    //TODO stampare lato client
                } else {
                    processWorkerPlayerChoice(message);
                }
            }

        }
    }


}
