package de.rwth_erstis.discordbot_jvm.apis;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class ChuckNorrisJokesApi extends API<String> {
    private static final ChuckNorrisJokesApi instance=new ChuckNorrisJokesApi();

    public static ChuckNorrisJokesApi getInstance() {
        return instance;
    }

    public ChuckNorrisJokesApi() {
        super(10);
    }

    @Override
    protected String requestObject() {
        try {
            String json=readUrl("https://api.chucknorris.io/jokes/random");
            JsonObject obj=JsonParser.parseString(json).getAsJsonObject();
            return obj.get("value").getAsString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Can´t find joke";
    }
}
