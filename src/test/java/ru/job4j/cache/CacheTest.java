package ru.job4j.cache;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class CacheTest {

    @Test
    public void add() {
        Cache cache = new Cache();
        Base b1 = new Base(1, 0);
        Base b2 = new Base(2, 0);
        cache.add(b1);
        cache.add(b2);
        assertThat(cache.get(b1.getId()), is(b1));
        assertThat(cache.get(b2.getId()), is(b2));
    }

    @Test
    public void delete() {
        Cache cache = new Cache();
        Base b1 = new Base(1, 0);
        Base b2 = new Base(2, 0);
        cache.add(b1);
        cache.add(b2);
        cache.delete(b2);
        cache.delete(b2);
        assertThat(cache.get(b1.getId()), is(b1));
        assertNull(cache.get(b2.getId()));
    }

    @Test
    public void update() {
        Cache cache = new Cache();
        Base b1 = new Base(1, 0);
        b1.setName("name1");
        cache.add(b1);
        assertThat(cache.get(b1.getId()).getName(), is("name1"));
        b1.setName("name2");
        assertTrue(cache.update(b1));
        assertThat(cache.get(b1.getId()).getName(), is("name2"));
    }

    @Test(expected = OptimisticException.class)
    public void diffVersion() {
        Cache cache = new Cache();
        cache.add(new Base(1, 1));
        cache.update(new Base(1, 2));
    }
}