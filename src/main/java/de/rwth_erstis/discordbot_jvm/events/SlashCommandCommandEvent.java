package de.rwth_erstis.discordbot_jvm.events;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;

public class SlashCommandCommandEvent extends CommandEvent {
    public SlashCommandCommandEvent(SlashCommandEvent jdaEvent) {
        super(jdaEvent);
    }

    @Override
    public void reply(String message) {
        SlashCommandEvent event = getSlashCommandEvent();
        event.reply(message).queue();
    }

    @Override
    public void reply(MessageEmbed embed) {
        getSlashCommandEvent().replyEmbeds(embed).queue();
    }
}
