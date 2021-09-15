package de.rwth_erstis.discordbot_jvm.apis;

import java.util.ArrayList;

public abstract class CachedAPI<T> extends API {

    protected final ArrayList<T> cache = new ArrayList<>();
    private final int cacheSize;

    protected CachedAPI() {
        this(10);
    }

    protected CachedAPI(int cacheSize) {
        this.cacheSize = cacheSize;
        new Thread(() -> {
            while (cache.size() < cacheSize)
                cache.add(requestObject());
        }).start();
    }

    public T getObject() {
        if (cache.size() <= cacheSize) {
            Thread thread = new Thread(() -> cache.add(requestObject()));
            thread.start();
            if (cache.size() == 0) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        return cache.remove(0);
    }

    protected abstract T requestObject();

}
