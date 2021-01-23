package server;

import root.Reservation;

import java.io.*;
import java.net.Socket;

public class ClientHandlingThread implements Runnable {
    private Socket clientSocket;
    private ObjectInputStream ois;
    private ReservationAgent reservationAgent;

    public ClientHandlingThread(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        this.ois = new ObjectInputStream(clientSocket.getInputStream());
        this.reservationAgent = new ReservationAgent();
    }

    @Override
    public void run() {
        try {
            Reservation res = (Reservation) ois.readObject();
            System.out.println("Resrvation");
            System.out.println(res.toString());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                this.ois.close();
                this.clientSocket.close();
                this.reservationAgent.stop();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
