package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                }
        );
        Thread second = new Thread(
                () -> {
                }
        );
        System.out.println("1: " + first.getState());
        System.out.println("2: " + second.getState());
        first.start();
        second.start();
        while (first.getState() != Thread.State.TERMINATED
                && second.getState() != Thread.State.TERMINATED) {
            System.out.println("1: " + first.getState());
            System.out.println("2: " + second.getState());
        }
        System.out.println("1: " + first.getState());
        System.out.println("2: " + second.getState());
        System.out.println("done");
    }
}