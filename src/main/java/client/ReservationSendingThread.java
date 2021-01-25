package client;

import root.Reservation;

import java.io.*;
import java.net.Socket;

public class ReservationSendingThread extends Thread {
    private Reservation reservation;
    private Socket socket;
    private ClientMain clientMain;

    public ReservationSendingThread(ClientMain clientMain) throws IOException {
        this.reservation = null;
        this.socket = new Socket("localhost", 34524);
        this.clientMain = clientMain;
    }

    public void send(Reservation reservation) {
        this.reservation = reservation;
    }

    private void sendResponse(String response) {
        this.clientMain.handleResponse(response);
    }

    @Override

    public void run() {
        OutputStream output;
        BufferedReader input;
        ObjectOutputStream objectOutput = null;

        try {
            output = this.socket.getOutputStream();
            input = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            objectOutput = new ObjectOutputStream(output);

            if (this.reservation != null) {
                objectOutput.writeObject(this.reservation);
            }
            this.sendResponse(input.readLine());

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                objectOutput.close();
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
