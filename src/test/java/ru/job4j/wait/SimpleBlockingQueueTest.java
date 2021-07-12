package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBlockingQueueTest {

    @Test
    public void offer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            queue.offer(10);
            queue.offer(20);
            queue.offer(30);
        });
        Thread consumer1 = new Thread(() -> {
            queue.poll();
            queue.poll();
        });
        producer.start();
        consumer1.start();
        producer.join();
        consumer1.join();
        assertEquals(queue.poll().intValue(), 30);
    }
}