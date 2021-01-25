package server;

import root.Reservation;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server implements Runnable {
    private final int PORT = 34524;
    private static ExecutorService pool = Executors.newCachedThreadPool();
    private ServerSocket serverSocket;
    private ServerMain serverGui;

    public Server(ServerMain serverGui) throws IOException {
        this.serverSocket = new ServerSocket(PORT);
        this.serverGui = serverGui;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("[SERVER] Waiting for client");
                Socket client = serverSocket.accept();
                System.out.println("[SERVER] Connected to client");

                ClientHandler clientThread = new ClientHandler(client, this.serverGui);

                pool.execute(clientThread);
            } catch (IOException | SQLException | ClassNotFoundException e) {
                System.out.println("Error with handling client");
                e.printStackTrace();
            }
        }
    }
}

