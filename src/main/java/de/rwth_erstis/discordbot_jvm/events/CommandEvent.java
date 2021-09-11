package de.rwth_erstis.discordbot_jvm.events;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.Event;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

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
