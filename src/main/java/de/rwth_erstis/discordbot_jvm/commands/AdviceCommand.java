package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.apis.AdviceApi;
import de.rwth_erstis.discordbot_jvm.events.CommandEvent;

public class AdviceCommand extends Command {
    public AdviceCommand() {
        super("advice", "Gives you advice");
    }

    @Override
    public void run(CommandEvent event) {
        event.reply(newBuilder().setTitle("Advice")
                .setDescription(AdviceApi.getInstance().getObject()));
    }
}
