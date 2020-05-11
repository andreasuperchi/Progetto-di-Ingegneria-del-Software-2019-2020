package it.polimi.ingsw.server;

import it.polimi.ingsw.view.Observer;

public interface ClientConnection {
    void closeConnection();

    void addObserver(Observer<Integer> observer);

    void asyncSend(Object message);
}
