package ru.job4j.concurrent;

public class Wget {
    public static void main(String[] args) {
        new Thread(() -> {
            int i = 0;
            while (i < 100) {
                System.out.print("\rLoading : " + ++i + "%");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }
}
