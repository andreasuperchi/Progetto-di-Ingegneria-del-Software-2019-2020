package it.polimi.ingsw.view;

import it.polimi.ingsw.model.Direction;
import it.polimi.ingsw.model.GodName;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Player;

public class RemoteView extends View {
    private boolean setupDone = false;

    protected RemoteView(Player player) {
        super(player);
    }

    private class StringReceiver implements Observer<String> {
        @Override
        public void update(String message) {
            if (!setupDone) {
                try {
                    GodName godName = GodName.parseInput(message);
                    processGodChoice(godName);
                } catch (IllegalArgumentException e) {
                    //TODO stampare lato client
                }
            } else {
                try {
                    Direction direction = Direction.parseInput(message);
                    processChoice(null, -1, direction);
                } catch (IllegalArgumentException e) {
                    //TODO stampare lato client
                }
            }
        }
    }

    private class IntReceiver implements Observer<Integer> {
        @Override
        public void update(Integer message) {
            if (!setupDone) {
                if (message <= 1 || message > 3) {
                    //TODO stampare lato client
                } else {
                    processNumberOfPlayersChoice(message);
                }
            } else {

            }

        }
    }


}
