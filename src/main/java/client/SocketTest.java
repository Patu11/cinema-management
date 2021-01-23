package client;

import root.Hall;
import root.Client;
import root.Movie;
import root.Reservation;

import java.io.IOException;
import java.sql.Date;

public class SocketTest {
    public static void main(String[] args) throws IOException {

        Hall cinema = new Hall(1, 50, 50);
        Movie movie = new Movie("Test title", 12, 120, "Comedy", 5.0);
        Client client = new Client("John", "Kowalski", Date.valueOf("1997-12-12"));
        Date date = Date.valueOf("2020-12-30");

        Reservation reservation = new Reservation(cinema, client, movie, 15, date, 20);

        ReservationSendingThread res = new ReservationSendingThread();
        res.start();
        res.send(reservation);
    }
}
