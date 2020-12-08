package root;

import java.io.Serializable;
import java.time.LocalDate;

//for future use
public class Movie implements Serializable {
    private String title;
    private int HallNumber;
    private double ticketPrice;
    private LocalDate startPlaying;
    private LocalDate stopPlaying;

    public Movie(String title, int hallNumber, double ticketPrice, LocalDate startPlaying, LocalDate stopPlaying) {
        this.title = title;
        HallNumber = hallNumber;
        this.ticketPrice = ticketPrice;
        this.startPlaying = startPlaying;
        this.stopPlaying = stopPlaying;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHallNumber() {
        return HallNumber;
    }

    public void setHallNumber(int hallNumber) {
        HallNumber = hallNumber;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public LocalDate getStartPlaying() {
        return startPlaying;
    }

    public void setStartPlaying(LocalDate startPlaying) {
        this.startPlaying = startPlaying;
    }

    public LocalDate getStopPlaying() {
        return stopPlaying;
    }

    public void setStopPlaying(LocalDate stopPlaying) {
        this.stopPlaying = stopPlaying;
    }
}

