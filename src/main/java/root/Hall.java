package root;

import java.io.Serializable;

public class Hall implements Serializable {
    private int hallNumber;
    private int seatsNumber;
    private int availableSeats;

    public Hall(int hallNumber, int seatsNumber, int availableSeats) {
        this.hallNumber = hallNumber;
        this.seatsNumber = seatsNumber;
        this.availableSeats = availableSeats;
    }

    public Hall(int seatsNumber, int availableSeats) {
        this.seatsNumber = seatsNumber;
        this.availableSeats = availableSeats;
    }

    public Hall() {
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public int getSeatsNumber() {
        return seatsNumber;
    }

    public void setSeatsNumber(int seatsNumber) {
        this.seatsNumber = seatsNumber;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void decreaseAvailableSeats() {
        this.availableSeats -= 1;
    }

    @Override
    public String toString() {
        return "Hall{" +
                "hallNumber=" + hallNumber +
                ", seatsNumber=" + seatsNumber +
                ", availableSeats=" + availableSeats +
                '}';
    }
}
