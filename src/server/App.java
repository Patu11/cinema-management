package server;

public class App {
    public static void main(String[] args) {
        CinemaHall cinema = new CinemaHall(1, 2, 5);

        ReservationAgent reservationAgent = new ReservationAgent();

        for (int i = 0; i < 10; i++) {
            reservationAgent.reserve(new Reservation(cinema, "Client" + i, 2));
        }
        reservationAgent.stop();
    }
}
