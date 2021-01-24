package root;

import java.util.Date;

public class ReservationData {
    private int hallId;
    private String firstName;
    private String lastName;
    private String title;
    private int seats;
    private Date date;
    private double price;

    public ReservationData(int hallId, String firstName, String lastName, String title, int seats, Date date, double price) {
        this.hallId = hallId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.seats = seats;
        this.date = date;
        this.price = price;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "ReservationData{" +
                "hallId=" + hallId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", title='" + title + '\'' +
                ", seats=" + seats +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
