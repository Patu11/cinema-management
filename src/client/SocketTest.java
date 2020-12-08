package client;

import root.CinemaHall;
import root.Movie;
import root.Reservation;

import java.io.IOException;
import java.time.LocalDate;

public class SocketTest {
    public static void main(String[] args) throws IOException {

        CinemaHall cinema = new CinemaHall(1, 2, 2);
        LocalDate startDate = LocalDate.of(2020, 12, 10);
        LocalDate endDate = LocalDate.of(2020, 12, 20);
        Movie movie = new Movie("Test title", 1, 5.2, startDate, endDate);

        cinema.addMovie(movie);

        Reservation reservation = new Reservation(cinema, "John", 1);

        ReservationSendingThread res = new ReservationSendingThread();
        res.start();
        res.send(reservation);
    }
}
