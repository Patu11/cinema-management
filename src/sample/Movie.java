package sample;

import java.util.Date;

//for future use
public class Movie {
    private String title;
    private int HallNumber;
    private double ticketPrice;
    private Date startPlaying;
    private Date stopPlaying;

    public Movie(String title, int hallNumber, double ticketPrice, Date startPlaying, Date stopPlaying) {
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

    public Date getStartPlaying() {
        return startPlaying;
    }

    public void setStartPlaying(Date startPlaying) {
        this.startPlaying = startPlaying;
    }

    public Date getStopPlaying() {
        return stopPlaying;
    }

    public void setStopPlaying(Date stopPlaying) {
        this.stopPlaying = stopPlaying;
    }
}

