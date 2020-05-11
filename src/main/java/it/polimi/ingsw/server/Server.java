package it.polimi.ingsw.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static final int PORT = 12345;
    private ServerSocket serverSocket;
    private ExecutorService executor = Executors.newFixedThreadPool(128);
    private Map<String, ClientConnection> waitingConnection = new HashMap<>();
    private int numberOfPlayers = 0;

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public synchronized void lobby(ClientConnection c, String name, int age) {
        waitingConnection.put(name, c);

    }

    public void run() {
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
