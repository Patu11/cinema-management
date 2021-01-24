package client;

import root.Reservation;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ReservationSendingThread extends Thread {
    private Reservation reservation;
    private Socket socket;

    public ReservationSendingThread() throws IOException {
        this.reservation = null;
        this.socket = new Socket("localhost", 34524);
    }

    public void send(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public void run() {
        OutputStream output = null;
        ObjectOutputStream objectOutput = null;

        try {
            output = this.socket.getOutputStream();
            objectOutput = new ObjectOutputStream(output);

            if (this.reservation != null) {
                objectOutput.writeObject(this.reservation);
            }

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
