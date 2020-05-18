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
        Scanner inName;
        String name;
        int numberOfPlayers;
        int intValue;

        try {
            in = new Scanner(socket.getInputStream());  //ricevo dal client
            out = new ObjectOutputStream(socket.getOutputStream()); //dà al client

            send("Welcome! What's your name?");
            name = in.nextLine();    //prende la stringa in ingresso

            if (server.getNumberOfPlayers() == 0) {
                server.setNumberOfPlayers(1);
                send("Insert the number of players");
                numberOfPlayers = Integer.parseInt(in.nextLine());
                server.setNumberOfPlayers(numberOfPlayers);
            }


            send("What's your age?");
            intValue = Integer.parseInt(in.nextLine());    //prende l'età
            server.lobby(this, name, intValue);

            while (isActive()) {
                intValue = Integer.parseInt(in.nextLine());
                notify(intValue);
            }
        } catch (IOException | NoSuchElementException e) {
            System.err.println("Error! " + e.getMessage());
        } finally {
            close();
        }
    }
}
