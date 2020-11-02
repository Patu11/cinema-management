package sample;

public class Reservation implements Runnable {

    private final CinemaHall cinemaHall;
    private final String name;
    private final int numberOfSeats;

    public Reservation(CinemaHall cinemaHall, String name, int numberOfSeats) {
        this.cinemaHall = cinemaHall;
        this.name = name;
        this.numberOfSeats = numberOfSeats;
    }

    @Override
    public void run() {
        this.cinemaHall.reserve(this.name, this.numberOfSeats);
    }
}

