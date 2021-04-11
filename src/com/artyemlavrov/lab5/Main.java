package com.artyemlavrov.lab5;

import com.artyemlavrov.lab5.server.Server;

public class Main {
    public static void main(String[] args) {
        //Client client = new Client();
        //client.run();

        Server server = new Server();
        server.run(args);
    }
}
