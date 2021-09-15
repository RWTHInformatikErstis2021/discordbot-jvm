package de.rwth_erstis.discordbot_jvm.events;

import de.rwth_erstis.discordbot_jvm.constants.BOT;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;

import java.util.ArrayList;

public abstract class CommandEvent {
    private final Event jdaEvent;

    public CommandEvent(Event jdaEvent) {
        this.jdaEvent = jdaEvent;
    }

    public abstract void reply(String message);

    public abstract void reply(MessageEmbed embed);

    public void reply(EmbedBuilder builder) {
        reply(builder.build());
    }

    //region getter
    public MessageReceivedEvent getMessageReceivedEvent() {
        return (MessageReceivedEvent) jdaEvent;
    }

    public SlashCommandEvent getSlashCommandEvent() {
        return (SlashCommandEvent) jdaEvent;
    }

    public String[] getArgs() {// TODO: Add better way to use arguments. Maybe something similar to JDAs slash commands
        if (isMessageEvent()) {
            String content = getMessageReceivedEvent().getMessage().getContentRaw();
            content = content.replaceFirst(BOT.PREFIX, "");

            String[] splitContent = content.split(" ", 2);

            if (splitContent.length == 1)
                return new String[0];

            String withoutCommand = splitContent[1];
            return withoutCommand.split(" ");
        } else {
            ArrayList<String> arguments = new ArrayList<>();
            for (OptionMapping o : getSlashCommandEvent().getOptions()) {
                arguments.add(o.getAsString());
            }
            return arguments.toArray(new String[0]);
        }
    }

    public Event getJdaEvent() {
        return jdaEvent;
    }

    public boolean isMessageEvent() {
        return this instanceof MessageCommandEvent;
    }

    public boolean isSlashCommandEvent() {
        return this instanceof SlashCommandCommandEvent;
    }

    //endregion
}
