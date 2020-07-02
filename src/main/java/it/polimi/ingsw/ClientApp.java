package it.polimi.ingsw;


import it.polimi.ingsw.client.Client;

import java.io.IOException;

public class ClientApp {
    public static void main(String[] args) {
        Client client = new Client("192.168.1.10", 12345);
        try {
            client.run();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
