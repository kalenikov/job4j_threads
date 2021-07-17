package ru.job4j.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailNotificationPool {
    private final ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    public void emailTo(User user) {
        pool.submit(() -> send(
                String.format("Notification %s to email %s", user.getUsername(), user.getEmail()),
                String.format("Add a new event to %s", user.getUsername()),
                user.getEmail()));
    }

    private void send(String subject, String body, String email) {
        System.out.println(Thread.currentThread().getName() + " send to " + email);
    }

    public void close() {
        pool.shutdown();
        while (!pool.isTerminated()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        EmailNotificationPool pool = new EmailNotificationPool();
        pool.emailTo(new User("name1", "email1"));
        pool.emailTo(new User("name2", "email2"));
        pool.close();
    }
}
