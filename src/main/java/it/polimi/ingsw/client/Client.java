package it.polimi.ingsw.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.logging.SocketHandler;

public class Client {
    private String ip;
    private int port;
    private boolean isActive;

    public Client(String ip, int port) {
        this.ip = ip;
        this.port = port;
        isActive = true;
    }

    public synchronized boolean isActive() {
        return isActive;
    }

    public synchronized void setActive(boolean active) {
        isActive = active;
    }

    /**
     * reads asynchronously from the specified socket and prints the messages to the client
     *
     * @param socketIn is the socket from which the client reads the messages
     * @return a new thread
     */
    public Thread asyncReadFromSocket(final ObjectInputStream socketIn) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if (inputObject instanceof String && (((String) inputObject).contains("You Win!") || ((String) inputObject).contains("You Lose!"))) {
                            System.out.println((String) inputObject);
                            setActive(false);
                        } else if (inputObject instanceof String && ((String) inputObject).contains("close")) {
                            System.out.println("You are out of the game. Disconnecting from the server...");
                            setActive(false);
                        } else if (inputObject instanceof String) {
                            System.out.println((String) inputObject);
                        } else {
                            throw new IllegalArgumentException();
                        }
                    }
                } catch (Exception e) {
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    /**
     * writes asynchronously from the specified socket and prints the messages to the client
     *
     * @param stdin     is the standard input, used by the user to write messages
     * @param socketOut is the socket where the messages are going to be sent
     * @return a new thread
     */
    public Thread asyncWriteFromSocket(final Scanner stdin, final PrintWriter socketOut) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (isActive()) {
                        String input = stdin.nextLine();
                        socketOut.println(input);
                        socketOut.flush();
                    }
                } catch (Exception e) {
                    setActive(false);
                }
            }
        });
        t.start();
        return t;
    }

    /**
     * initializes the basic tools that the client needs to work properly
     *
     * @throws IOException general exception
     */
    public void run() throws IOException {
        Socket socket = new Socket(ip, port);
        System.out.println("\u001b[32;1mConnection Established.\u001b[0m\n");
        ObjectInputStream socketIn = new ObjectInputStream(socket.getInputStream());
        PrintWriter socketOut = new PrintWriter(socket.getOutputStream());
        Scanner stdin = new Scanner(System.in);
        try {
            Thread t0 = asyncReadFromSocket(socketIn);
            Thread t1 = asyncWriteFromSocket(stdin, socketOut);
            t0.join();
            t1.join();
        } catch (InterruptedException | NoSuchElementException e) {
            System.out.println("Connection closed from the client side.");
        } finally {
            stdin.close();
            socketIn.close();
            socketOut.close();
            socket.close();
        }
    }
}
