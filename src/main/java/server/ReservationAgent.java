package server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ReservationAgent {
    private final ExecutorService executor;
    private final Semaphore semaphore;

    public ReservationAgent() {
        this.executor = Executors.newCachedThreadPool();
        this.semaphore = new Semaphore(1);
    }

    public void reserve(Runnable task) {
        try {
            this.semaphore.acquire();
            this.executor.execute(() -> {
                try {
                    task.run();
                } finally {
                    semaphore.release();
                }
            });
        } catch (InterruptedException e) {
            this.semaphore.release();
        }
    }

    public void stop() {
        this.executor.shutdown();
    }
}
