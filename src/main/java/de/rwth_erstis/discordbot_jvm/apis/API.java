package de.rwth_erstis.discordbot_jvm.apis;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public abstract class API<T> {

    private final ArrayList<T> cache = new ArrayList<>();
    protected final Gson gson = new Gson();

    protected API() {
        this(10);
    }

    protected API(int cacheSize) {
        new Thread(() -> {
            for (int i = 0; i < cacheSize; i++) {
                cache.add(requestObject());
            }
        }).start();
    }

    public T getObject() {
        Thread thread = new Thread(() -> cache.add(requestObject()));
        thread.start();
        if (cache.size() == 0) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return cache.remove(0);
    }

    protected abstract T requestObject();

    protected String readUrl(String urlString) throws Exception {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuilder buffer = new StringBuilder();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1)
                buffer.append(chars, 0, read);

            return buffer.toString();
        } finally {
            if (reader != null)
                reader.close();
        }
    }

}
