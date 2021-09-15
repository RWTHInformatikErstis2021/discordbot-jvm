package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.events.CommandEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.awt.*;

public abstract class Command {
    private final String name, description;
    private final String[] aliases;

    public Command(String name, String description, String... aliases) {
        this.name = name;
        this.description = description;
        this.aliases = aliases;
    }

    public abstract void run(CommandEvent event);

    public CommandData getCommandData() {
        return new CommandData(getName().toLowerCase(), getDescription());
    }

    protected EmbedBuilder newBuilder() {//allows to configure default values for embeds like color
        EmbedBuilder newBuilder = new EmbedBuilder();
        newBuilder.setColor(Color.black);//Black—like my soul
        return newBuilder;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String[] getAliases() {
        return aliases;
    }
}
