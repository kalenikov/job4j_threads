package ru.job4j.jcip;

import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

@ThreadSafe
public class UserStorage {
    private final HashMap<Integer, User> map = new HashMap<>();

    public synchronized boolean add(User user) {
        return map.putIfAbsent(user.getId(), user) != null;
    }

    public synchronized boolean update(User user) {
        return map.replace(user.getId(), user) != null;
    }

    public synchronized boolean delete(User user) {
        return map.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User sender = map.get(fromId);
        User recipient = map.get(toId);
        if (sender == null || recipient == null || sender.getAmount() < amount) {
            return false;
        }
        sender.setAmount(sender.getAmount() - amount);
        recipient.setAmount(recipient.getAmount() + amount);
        return true;
    }
}
