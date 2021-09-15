package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.apis.TrumpQuotesApi;
import de.rwth_erstis.discordbot_jvm.events.CommandEvent;

public class TrumpQuoteCommand extends Command{

    public TrumpQuoteCommand() {
        super("trump", "sends trump quotes", "shit");
    }

    @Override
    public void run(CommandEvent event) {
        event.reply(TrumpQuotesApi.getApi().getObject().createEmbed(newBuilder()));
    }
}
