package server;

import root.Reservation;

import java.io.*;
import java.net.Socket;

public class ClientHandlingThread extends Thread {
    private Socket clientSocket;
    private boolean running;

    public ClientHandlingThread(Socket clientSocket) {
        super();
        this.clientSocket = clientSocket;
        this.running = true;
    }

    @Override
    public void run() {
        ObjectInputStream ois = null;
        ReservationAgent reservationAgent = null;
        try {
            ois = new ObjectInputStream(clientSocket.getInputStream());

            while (running) {

                try {
                    Reservation res = (Reservation) ois.readObject();
                    reservationAgent = new ReservationAgent();
                    reservationAgent.reserve(res);
                    System.out.println(res.toString());
                } catch (EOFException e) {
                    System.out.println("Closed");
                    reservationAgent.stop();
                    this.running = false;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
                clientSocket.close();
                System.out.println("Stopped");

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
