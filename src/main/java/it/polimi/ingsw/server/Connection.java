package it.polimi.ingsw.server;

import it.polimi.ingsw.view.Observable;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Connection extends Observable<Integer> implements ClientConnection, Runnable {
    private final Socket socket;
    private final Server server;
    private ObjectOutputStream out;

    private boolean active = true;

    public synchronized boolean isActive() {
        return active;
    }

    /**
     * creates a connection based on the server and the socket that are specified
     *
     * @param socket is the socket to whom the connection is going to link
     * @param server is the server to whom the connection is going to link
     */
    public Connection(Socket socket, Server server) {
        this.socket = socket;
        this.server = server;
    }

    /**
     * sends a message to the client
     *
     * @param message is the message to be sent to the client
     */
    private synchronized void send(Object message) {
        try {
            out.reset();
            out.writeObject(message);
            out.flush();
        } catch (IOException e) {
            System.err.println("Unexpected Client disconnection!");
        }
    }

    /**
     * closes the connection and sets the flg "active" to false
     */
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

    /**
     * sends a message in an asynchronous way
     *
     * @param message is the message to be sent to the client
     */
    @Override
    public void asyncSend(final Object message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                send(message);
            }
        }).start();
    }

    /**
     * closes the connection
     */
    private void close() {
        closeConnection();
        System.out.println("Deregistering client...");
        server.deregisterConnection(this);
        System.out.println("Done!");
    }


    /**
     * Asks the player informations before starting the game, then sets up the connection
     */
    @Override
    public void run() {
        Scanner in;
        String name;
        int numberOfPlayers;
        int intValue;

        try {
            in = new Scanner(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());

            send("\033[31;1mWelcome to Santorini! \n\u001b[0mWaiting for the other players...\n ");

            synchronized (server) {
                if (server.getNumberOfPlayers() == 0) {
                    send("Insert the number of players: ");
                    numberOfPlayers = Integer.parseInt(in.nextLine());
                    server.setNumberOfPlayers(numberOfPlayers);
                }
            }

            send("\nWhat's your name?");
            name = in.nextLine();

            send("\nWhat's your age?");
            intValue = Integer.parseInt(in.nextLine());
            server.lobby(this, name, intValue);

            while (isActive()) {
                intValue = Integer.parseInt(in.nextLine());
                notify(intValue);
            }
        } catch (IOException | NoSuchElementException e) {
            System.err.println("Input line not found!");
        } finally {
            close();
        }
    }
}
