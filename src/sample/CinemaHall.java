package sample;

import java.util.Arrays;
import java.util.List;

public class CinemaHall {
    private int HallNumber;
    private int[][] seats;
    private int rows;
    private int columns;
    private int availableSeats;
    private List<Movie> listOfMovies;   //for future use

    public CinemaHall(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.seats = new int[this.rows][this.columns];
        this.availableSeats = this.rows * this.columns;

        //for future use
        //0 - seat is available
        //1 - reserved but waiting for payment
        //2 - not available
        for (int[] seat : this.seats) {
            Arrays.fill(seat, 0);
        }
    }

    //basic version of reservation
    public synchronized void reserve(String name, int numberOfSeats) {
        if (this.availableSeats >= numberOfSeats && numberOfSeats > 0) {
            System.out.println(name + " reserved " + numberOfSeats + " seats");
            this.availableSeats -= numberOfSeats;
        } else {
            System.out.println(name + " cannot reserve");
        }
    }
}
