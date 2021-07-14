package ru.job4j.cas;

import net.jcip.annotations.NotThreadSafe;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@NotThreadSafe
public class StackTest {

    @Test
    public void when3PushThen3Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void when1PushThen1Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        assertThat(stack.poll(), is(1));
    }

    @Test
    public void when2PushThen2Poll() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
    }
}