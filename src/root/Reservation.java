package root;

import java.io.Serializable;

public class Reservation implements Runnable, Serializable {

    private final CinemaHall cinemaHall;
    private final String name;
    private final int numberOfSeatsToReserve;

    public Reservation(CinemaHall cinemaHall, String name, int numberOfSeatsToReserve) {
        this.cinemaHall = cinemaHall;
        this.name = name;
        this.numberOfSeatsToReserve = numberOfSeatsToReserve;
    }

    @Override
    public void run() {
        if (cinemaHall.getAvailableSeats() >= this.numberOfSeatsToReserve && this.numberOfSeatsToReserve > 0) {
            System.out.println(this.name + " reserved " + this.numberOfSeatsToReserve + " seats");
            cinemaHall.decreaseAvailableSeats(this.numberOfSeatsToReserve);
        } else {
            System.out.println(this.name + " cannot reserve");
        }
    }

    //for testing
    @Override
    public String toString() {
        return "Reservation{" +
                "cinemaHall=" + cinemaHall +
                ", name='" + name + '\'' +
                ", numberOfSeatsToReserve=" + numberOfSeatsToReserve +
                '}';
    }
}

