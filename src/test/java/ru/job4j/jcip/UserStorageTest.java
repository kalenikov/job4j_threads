package ru.job4j.jcip;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {
    private static User USER_1 = new User(1, 100);
    private static User USER_2 = new User(2, 100);

    @Test
    public void add() {
        UserStorage us = new UserStorage();
        assertThat(us.add(USER_1), is(true));
        assertThat(us.add(USER_1), is(false));
    }

    @Test
    public void delete() {
        UserStorage us = new UserStorage();
        us.add(USER_1);
        assertThat(us.delete(USER_1), is(true));
        assertThat(us.delete(USER_1), is(false));
    }

    @Test
    public void transfer() throws InterruptedException {
        UserStorage us = new UserStorage();
        us.add(USER_1);
        us.add(USER_2);
        Thread t1 = new Thread(() -> us.transfer(USER_1.getId(), USER_2.getId(), 50));
        Thread t2 = new Thread(() -> us.transfer(USER_2.getId(), USER_1.getId(), 1));
        t1.start();
        t1.join();
        t2.start();
        t2.join();
        assertThat(USER_1.getAmount(), is(51));
        assertThat(USER_2.getAmount(), is(149));
    }
}