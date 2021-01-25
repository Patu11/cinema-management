package server;

import root.MySQLAccess;
import root.Reservation;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    MySQLAccess sql;
    private final Socket client;
    private ObjectInputStream in;
    private PrintWriter response;
    private ReservationAgent reservationAgent;
    private ServerMain serverGui;

    public ClientHandler(Socket clientSocket, ServerMain serverGui) throws IOException, SQLException, ClassNotFoundException {
        this.client = clientSocket;
        this.reservationAgent = new ReservationAgent();
        this.serverGui = serverGui;
        this.sql = new MySQLAccess();
    }

    private void sendReservation(Reservation reservation) throws SQLException {
        this.serverGui.setReservation(reservation);
    }

    @Override
    public void run() {
        try {
            this.in = new ObjectInputStream(this.client.getInputStream());
            this.response = new PrintWriter(this.client.getOutputStream(), true);
            Reservation res = (Reservation) this.in.readObject();
            this.reservationAgent.reserve(res);
            if (res.getCinemaHall().getAvailableSeats() - res.getSeatNumber() >= 0) {
                response.println("OK");
            } else {
                response.println("BLOCKED");
            }

            this.sendReservation(res);

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (this.in != null) {
                    this.in.close();
                } else {
                    System.out.println("Client disconnected");
                }
                this.client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
