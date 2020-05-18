package it.polimi.ingsw.server;

import it.polimi.ingsw.view.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection extends Observable<Integer> implements ClientConnection, Runnable {
    private Socket socket;
    private Server server;
    private ObjectOutputStream out;

    private boolean active = true;

    public synchronized boolean isActive() {
        return active;
    }

    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    private synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public void closeConnection() {
        send("Connection closed!");
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("Error when closing socket!");
        }
        active = false;
    }

    @Override
    public void asyncSend(Object message) {

    }

    private void close() {
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }

    @Override
    public void run() {
        Scanner in;
        String name;
        int numberOfPlayers;
        int intValue;

        try {
            in = new Scanner(socket.getInputStream());  //ricevo dal client
            out = new ObjectOutputStream(socket.getOutputStream()); //dà al client

            if (server.getNumberOfPlayers() == 0) {
                send("Insert the number of players\n");
                numberOfPlayers = in.nextInt();
                server.setNumberOfPlayers(numberOfPlayers);
            }

            send("Welcome! What's your name?\n");
            name = in.nextLine();    //prende la stringa in ingresso

            send("What's your age?\n");
            intValue = in.nextInt();    //prende l'età
            server.lobby(this, name, intValue);

            while (isActive()) {
                intValue = in.nextInt();
                notify(intValue);
            }
        } catch (IOException | NoSuchElementException e) {
            System.err.println("Error! " + e.getMessage());
        } finally {
            close();
        }
    }
}
