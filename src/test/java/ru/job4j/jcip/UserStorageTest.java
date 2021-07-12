package ru.job4j.jcip;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    @Test
    public void add() {
        UserStorage us = new UserStorage();
        User user1 = new User(1, 100);
        assertThat(us.add(user1), is(true));
        assertThat(us.add(user1), is(false));
    }

    @Test
    public void delete() {
        UserStorage us = new UserStorage();
        User user1 = new User(1, 100);
        us.add(user1);
        assertThat(us.delete(user1), is(true));
        assertThat(us.delete(user1), is(false));
    }

    @Test
    public void transfer() throws InterruptedException {
        UserStorage us = new UserStorage();
        User user1 = new User(1, 100);
        User user2 = new User(2, 100);
        us.add(user1);
        us.add(user2);
        Thread t1 = new Thread(() -> us.transfer(user1.getId(), user2.getId(), 50));
        Thread t2 = new Thread(() -> us.transfer(user2.getId(), user1.getId(), 1));
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        assertThat(user1.getAmount(), is(51));
        assertThat(user2.getAmount(), is(149));
    }
}