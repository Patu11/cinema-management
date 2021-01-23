package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static void main(String[] args) throws IOException {
        final int PORT = 34524;
        ServerSocket socket = new ServerSocket(PORT);

        System.out.println("Server on port:" + PORT);

        while (true) {
            try {
                Socket client = socket.accept();

                System.out.println("Connection from: " + client.getInetAddress());

//                ClientHandlingThread handling = new ClientHandlingThread(client);
//                handling.run();
                Thread thread = new Thread(new ClientHandlingThread(client));
                thread.start();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
