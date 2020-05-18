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

    public Thread asyncReadFromSocket(final ObjectInputStream socketIn) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {     //Legge messaggi da RemoteView
                try {
                    while (isActive()) {
                        Object inputObject = socketIn.readObject();
                        if (inputObject instanceof String && inputObject.equals("You Win!!!!")) {
                            System.out.println((String) inputObject);
                            setActive(false);
                        } else if (inputObject instanceof String && ((String) inputObject).contains("You Lose")) {
                            System.out.println((String) inputObject);
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

    public Thread asyncWriteFromSocket(final Scanner stdin, final PrintWriter socketOut) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {        //Invia messaggi a RemoteView
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

    public void run() throws IOException {         //Inizializza il client e stabilisce la connessione con il server
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
