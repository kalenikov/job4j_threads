package ru.job4j.pool;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSearcher<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final int from;
    private final int to;
    private final T obj;

    public ForkJoinSearcher(T[] array, int from, int to, T obj) {
        this.array = array;
        this.from = from;
        this.to = to;
        this.obj = obj;
    }

    @Override
    protected Integer compute() {
        if ((to - from) <= 10) {
            for (int i = 0; i < to; i++) {
                if (array[i].equals(obj)) {
                    return i;
                }
            }
            return -1;
        }
        int mid = (from + to) / 2;
        ForkJoinSearcher<T> leftSearch = new ForkJoinSearcher<>(array, from, mid, obj);
        ForkJoinSearcher<T> rightSearch = new ForkJoinSearcher<>(array, mid + 1, to, obj);
        leftSearch.fork();
        rightSearch.fork();
        Integer left = leftSearch.join();
        Integer right = rightSearch.join();
        return Math.max(left, right);
    }

    public static <T> Integer find(T[] array, T obj) {
        ForkJoinPool pool = new ForkJoinPool();
        return pool.invoke(new ForkJoinSearcher<>(array, 0, array.length - 1, obj));
    }

    public static void main(String[] args) {
        Integer[] ar = Arrays.stream(new Random().ints(0, 1000)
                .limit(1000)
                .toArray())
                .boxed()
                .toArray(Integer[]::new);
        System.out.println(ForkJoinSearcher.find(ar, 50));
    }
}
