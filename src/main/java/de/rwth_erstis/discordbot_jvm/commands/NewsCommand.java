package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.apis.NewsApi;
import de.rwth_erstis.discordbot_jvm.events.CommandEvent;

public class NewsCommand extends Command {

    public NewsCommand() {
        super("news", "Sends news articles");
    }

    @Override
    public void run(CommandEvent event) {
        event.reply(NewsApi.getInstance().getObject().createEmbed(newBuilder()));
    }
}
