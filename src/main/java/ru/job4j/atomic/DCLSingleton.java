package ru.job4j.atomic;

public class DCLSingleton {
    private volatile static DCLSingleton instance;

    public static DCLSingleton instOf() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }

    private DCLSingleton() {
    }
}
