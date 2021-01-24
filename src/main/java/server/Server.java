package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final int PORT = 34524;
    private static ExecutorService pool = Executors.newCachedThreadPool();
    private ServerSocket serverSocket;

    public Server() throws IOException {
        this.serverSocket = new ServerSocket(PORT);
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("[SERVER] Waiting for client");
                Socket client = serverSocket.accept();
                System.out.println("[SERVER] Connected to client");

                ClientHandler clientThread = new ClientHandler(client);
                pool.execute(clientThread);
            } catch (IOException e) {
                System.out.println("Error with handling client");
                e.printStackTrace();
            }
        }
    }
}

