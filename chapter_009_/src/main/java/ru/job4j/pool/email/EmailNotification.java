package ru.job4j.pool.email;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotification {
    private ExecutorService executorService = Executors.newFixedThreadPool(
            Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        executorService.submit(() ->
        {
            final String subject = String.format("Notification {%s} to email {%S}", user.getUsername(), user.getEmail());
            final String body = String.format("Add a new event to {%s}", user.getUsername());

            send(subject, body, user.getEmail());
        });
    }

    public void close() {
        this.executorService.shutdown();
        while (!executorService.isTerminated()) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void send(String subject, String body, String email) {
    }
}
