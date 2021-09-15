package de.rwth_erstis.discordbot_jvm.apis;

public class TrumpQuotesApi extends API<TrumpQuote> {
    private static final TrumpQuotesApi api=new TrumpQuotesApi();

    public static TrumpQuotesApi getApi() {
        return api;
    }

    public TrumpQuotesApi() {
        super(10);
    }

    @Override
    protected TrumpQuote requestObject() {
        try {
            String json=readUrl("https://api.tronalddump.io/random/quote");
            TrumpQuote quote= gson.fromJson(json,TrumpQuote.class);
            return quote;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new TrumpQuote();
    }
}
