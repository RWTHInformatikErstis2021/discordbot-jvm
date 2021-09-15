package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.apis.InsultApi;
import de.rwth_erstis.discordbot_jvm.events.CommandEvent;

public class InsultCommand extends Command {

    public InsultCommand() {
        super("insult", "Insults you");
    }

    @Override
    public void run(CommandEvent event) {
        event.reply(newBuilder().setTitle("Insult")
                .setDescription(InsultApi.getInstance().getObject()));
    }
}
