package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.CommandHandler;
import de.rwth_erstis.discordbot_jvm.events.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;

import java.util.Collection;

public class Help extends Command {
    public Help() {
        super("help", "gives help");
    }

    @Override
    public void run(CommandEvent event) {
        Collection<Command> commands = getCmdHandler().getCommands();
        EmbedBuilder embedBuilder = newBuilder();
        embedBuilder.setTitle("Help for commands", "https://youtu.be/watch?v=dQw4w9WgXcQ");
        for (Command command : commands) {
            embedBuilder.addField(command.getName(), command.getDescription(), true);
        }
        event.reply(embedBuilder);
    }
}
