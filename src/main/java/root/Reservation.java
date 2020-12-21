package root;

import java.io.Serializable;
import java.util.Date;

public class Reservation implements Runnable, Serializable {

    private final Hall cinemaHall;
    private final Client client;
    private final Movie movie;
    private final int seatNumber;
    private final Date date;

    public Reservation(Hall cinemaHall, Client client, Movie movie, int seatNumber, Date date) {
        this.cinemaHall = cinemaHall;
        this.client = client;
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.date = date;
    }

    @Override
    public void run() {
        if (cinemaHall.getAvailableSeats() >= 0) {
            System.out.println(this.client.getFirstName() + " reserved seat number: " + this.seatNumber + " on movie: " + this.movie.getTitle());
            cinemaHall.decreaseAvailableSeats();
        } else {
            System.out.println(this.client.getFirstName() + " cannot reserve");
        }
    }

    public Hall getCinemaHall() {
        return cinemaHall;
    }

    public Client getClient() {
        return client;
    }

    public Movie getMovie() {
        return movie;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public Date getDate() {
        return date;
    }

    //for testing1
    @Override
    public String toString() {
        return "Reservation{" +
                "cinemaHall=" + cinemaHall +
                ", name='" + client.getFirstName() + '\'' +
                ", seatNumber='" + seatNumber + '\'' +
                ", title='" + movie.getTitle() + '\'' +
                '}';
    }
}

