package root;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Date;

public class Reservation implements Runnable, Serializable {

    private Hall cinemaHall;
    private Client client;
    private Movie movie;
    private int seatNumber;
    private Date date;
    private double price;

    public Reservation(Hall cinemaHall, Client client, Movie movie, int seatNumber, Date date, float price) {
        this.cinemaHall = cinemaHall;
        this.client = client;
        this.movie = movie;
        this.seatNumber = seatNumber;
        this.date = date;
        this.price = price;
    }

    public Reservation() {
    }

    @Override
    public void run() {
        try {
            if (this.cinemaHall.getAvailableSeats() > 0 && this.cinemaHall.decreaseAvailableSeats(this.seatNumber) && this.seatNumber > 0) {
            } else {
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCinemaHall(Hall cinemaHall) {
        this.cinemaHall = cinemaHall;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setDate(Date date) {
        this.date = date;
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

