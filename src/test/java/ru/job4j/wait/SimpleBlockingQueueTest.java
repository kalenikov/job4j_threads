package ru.job4j.wait;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SimpleBlockingQueueTest {

    @Test
    public void offer() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<>(3);
        Thread producer = new Thread(() -> {
            try {
                queue.offer(10);
                queue.offer(20);
                queue.offer(30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread consumer1 = new Thread(() -> {
            try {
                queue.poll();
                queue.poll();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        producer.start();
        consumer1.start();
        producer.join();
        consumer1.join();
        assertEquals(queue.poll().intValue(), 30);
    }
}