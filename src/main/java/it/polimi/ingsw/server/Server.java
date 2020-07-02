package it.polimi.ingsw.server;

import it.polimi.ingsw.controller.Controller;
import it.polimi.ingsw.model.Model;
import it.polimi.ingsw.model.Outcome;
import it.polimi.ingsw.model.Player;
import it.polimi.ingsw.view.RemoteView;
import it.polimi.ingsw.view.View;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;
    private final ServerSocket serverSocket;
    private final ExecutorService executor = Executors.newFixedThreadPool(128);
    private final Map<String, ClientConnection> waitingConnection = new HashMap<>();
    private final Map<String, Integer> mapNameAge = new HashMap<>();
    private final ArrayList<ClientConnection> playingConnection = new ArrayList<>();

    private int numberOfPlayers;

    /**
     * builds a new server that has a serverSocket
     *
     * @throws IOException general exception
     */
    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.numberOfPlayers = 0;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }


    /**
     * Deregisters the connection. If only two playing connections are remaining,
     * it removes all the connections from the playingConnections arrayList
     *
     * @param c represents the connection which has to be deregistered
     */
    public synchronized void deregisterConnection(ClientConnection c) {
        if (playingConnection.size() == 3) {
            playingConnection.remove(c);

            Iterator<String> iterator = waitingConnection.keySet().iterator();
            while (iterator.hasNext()) {
                if (waitingConnection.get(iterator.next()) == c) {
                    iterator.remove();
                }
            }
        } else {
            playingConnection.clear();
            waitingConnection.clear();
            numberOfPlayers = 0;
        }
    }

    /**
     * Makes players join the game, initializing every view,  model and  controller.
     * If the players number is lower than requested, players who have already joined will be put on hold.
     *
     * @param c    represents user's connection
     * @param name represents user's name
     * @param age  represents user's age
     */
    public synchronized void lobby(ClientConnection c, String name, int age) {
        waitingConnection.put(name, c);
        mapNameAge.put(name, age);

        if (waitingConnection.size() == numberOfPlayers) {
            List<String> keys = new ArrayList<>(waitingConnection.keySet());
            ArrayList<Player> players = new ArrayList<>();

            if (numberOfPlayers == 2) {
                ClientConnection c1 = waitingConnection.get(keys.get(0));
                ClientConnection c2 = waitingConnection.get(keys.get(1));
                Player player1 = new Player(keys.get(0), mapNameAge.get(keys.get(0)), "\u001b[32m@");
                players.add(player1);
                Player player2 = new Player(keys.get(1), mapNameAge.get(keys.get(1)), "\u001b[36m#");
                players.add(player2);
                View player1View = new RemoteView(player1, c1);
                View player2View = new RemoteView(player2, c2);

                Model model = new Model(players, numberOfPlayers);
                Controller controller = new Controller(model);

                model.addObserver(player1View);
                model.addObserver(player2View);
                player1View.addObserver(controller);
                player2View.addObserver(controller);
                playingConnection.add(c1);
                playingConnection.add(c2);

                c1.asyncSend(model.getOutcome().printOutcome());
                c2.asyncSend("\u001b[33;1mWaiting for the other players to make their moves...\u001b[0m");

            } else if (numberOfPlayers == 3) {
                ClientConnection c1 = waitingConnection.get(keys.get(0));
                ClientConnection c2 = waitingConnection.get(keys.get(1));
                ClientConnection c3 = waitingConnection.get(keys.get(2));

                Player player1 = new Player(keys.get(0), mapNameAge.get(keys.get(0)), "\u001b[32m@");
                players.add(player1);
                Player player2 = new Player(keys.get(1), mapNameAge.get(keys.get(1)), "\u001b[36m#");
                players.add(player2);
                Player player3 = new Player(keys.get(2), mapNameAge.get(keys.get(2)), "\u001b[35m$");
                players.add(player3);

                View player1View = new RemoteView(player1, c1);
                View player2View = new RemoteView(player2, c2);
                View player3View = new RemoteView(player3, c3);

                Model model = new Model(players, numberOfPlayers);
                Controller controller = new Controller(model);

                model.addObserver(player1View);
                model.addObserver(player2View);
                model.addObserver(player3View);

                player1View.addObserver(controller);
                player2View.addObserver(controller);
                player3View.addObserver(controller);

                playingConnection.add(c1);
                playingConnection.add(c2);
                playingConnection.add(c3);

                c1.asyncSend(model.getOutcome().printOutcome());
                c2.asyncSend("\u001b[33;1m Waiting for the other players to make their moves...\u001b[0m");
                c3.asyncSend("\u001b[33;1m Waiting for the other players to make their moves...\u001b[0m");

            }
            waitingConnection.clear();


        }

    }

    /**
     * Waits for user's connection and then initializes a connection
     * between server and client
     */
    public void run() {
        System.out.println("\u001b[32;1mServer running...");
        while (true) {
            try {
                Socket newSocket = serverSocket.accept();   //aspetta la connessione in ingresso
                Connection socketConnection = new Connection(newSocket, this);  //creo connessione tra client e server
                executor.submit(socketConnection);  //stabilisce la connessione
            } catch (IOException e) {
                System.out.println("Connection Error!");
            }
        }
    }
}
