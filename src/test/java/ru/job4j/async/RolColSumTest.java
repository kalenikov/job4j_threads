package ru.job4j.async;

import org.junit.Test;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;

public class RolColSumTest {

    @Test
    public void sum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] sums = RolColSum.sum(matrix);
        assertNotNull(sums);
        assertThat(sums.length, is(3));
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[2].getRowSum(), is(24));
        assertThat(sums[0].getColSum(), is(12));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[2].getColSum(), is(18));
    }

    @Test
    public void asyncSum() throws ExecutionException, InterruptedException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        Sums[] sums = RolColSum.asyncSum(matrix);
        assertNotNull(sums);
        assertThat(sums.length, is(3));
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[2].getRowSum(), is(24));
        assertThat(sums[0].getColSum(), is(12));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[2].getColSum(), is(18));
    }
}