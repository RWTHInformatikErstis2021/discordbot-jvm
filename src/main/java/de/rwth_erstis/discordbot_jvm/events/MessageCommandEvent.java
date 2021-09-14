package de.rwth_erstis.discordbot_jvm.events;

import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class MessageCommandEvent extends CommandEvent {
    public MessageCommandEvent(MessageReceivedEvent jdaEvent) {
        super(jdaEvent);
    }

    @Override
    public void reply(String message) {
        MessageReceivedEvent event = getMessageReceivedEvent();
        event.getChannel().sendMessage(message).queue();
    }

    @Override
    public void reply(MessageEmbed embed) {
        getMessageReceivedEvent().getChannel().sendMessage(embed).queue();
    }


}
