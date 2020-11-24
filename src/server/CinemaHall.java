package server;

import java.util.Arrays;
import java.util.List;

public class CinemaHall {
    private int hallNumber;
    private int[][] seats;
    private int rows;
    private int columns;
    private int availableSeats;
    private List<Movie> listOfMovies;   //for future use

    public CinemaHall(int hallNumber, int rows, int columns) {
        this.hallNumber = hallNumber;
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
    
    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public void decreaseAvailableSeats(int number) {
        this.availableSeats -= number;
    }

    public void addMovie(Movie movie) {
        this.listOfMovies.add(movie);
    }
}
