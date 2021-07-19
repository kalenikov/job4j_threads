package ru.job4j.async;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class RolColSum {

    public static Sums[] sum(int[][] matrix) {
        Sums[] rsl = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            rsl[i] = new Sums();
            rsl[i].setRowSum(Arrays.stream(matrix[i]).sum());
            int finalI = i;
            rsl[i].setColSum(Arrays.stream(matrix).mapToInt(e -> e[finalI]).sum());
        }
        return rsl;
    }

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] rsl = new Sums[matrix.length];
        Map<Integer, CompletableFuture<Sums>> futures = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            futures.put(i, getSums(matrix, i));
        }
        for (Integer i : futures.keySet()) {
            rsl[i] = futures.get(i).get();
        }
        return rsl;
    }

    public static CompletableFuture<Sums> getSums(int[][] matrix, int i) {
        return CompletableFuture.supplyAsync(() -> {
            Sums rsl = new Sums();
            rsl.setRowSum(Arrays.stream(matrix[i]).sum());
            rsl.setColSum(Arrays.stream(matrix).mapToInt(r -> r[i]).sum());
            return rsl;
        });
    }
}