package server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {
    public static void main(String[] args) {
        CinemaHall cinema = new CinemaHall(5, 9);

        ExecutorService ex = Executors.newCachedThreadPool();

        for (int i = 0; i < 10; i++) {
            ex.execute(new Reservation(cinema, "Reservation " + i, 5));
        }
        ex.shutdownNow();
    }
}
