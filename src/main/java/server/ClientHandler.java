package server;

import root.Reservation;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private final Socket client;
    private ObjectInputStream in;
    private ReservationAgent reservationAgent;

    public ClientHandler(Socket clientSocket) throws IOException {
        this.client = clientSocket;
        this.reservationAgent = new ReservationAgent();
    }

    @Override
    public void run() {
        try {
            this.in = new ObjectInputStream(this.client.getInputStream());
            Reservation res = (Reservation) this.in.readObject();
            this.reservationAgent.reserve(res);
            System.out.println(res.toString());
        } catch (IOException | ClassNotFoundException e) {

        } finally {
            try {
                this.in.close();
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
