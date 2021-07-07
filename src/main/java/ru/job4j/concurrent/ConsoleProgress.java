package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {
    @Override
    public void run() {
        String[] ar = {"--", "\\", "|", "/"};
        int i = 0;
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rload: " + ar[i % ar.length]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i = (i == ar.length) ? 0 : ++i;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(5000);
        progress.interrupt();
    }
}
