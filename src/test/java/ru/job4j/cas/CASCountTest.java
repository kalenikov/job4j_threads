package ru.job4j.cas;

import org.junit.Test;

import java.util.stream.IntStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class CASCountTest {

    @Test
    public void whenIncrement() throws InterruptedException {
        CASCount count = new CASCount();
        Thread t1 = new Thread(() -> IntStream.range(1, 6).forEach(el -> count.increment()));
        Thread t2 = new Thread(() -> IntStream.range(1, 6).forEach(el -> count.increment()));
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        assertThat(count.get(), is(10));
    }

}