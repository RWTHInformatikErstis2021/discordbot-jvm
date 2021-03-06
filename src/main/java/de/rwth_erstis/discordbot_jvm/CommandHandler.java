package de.rwth_erstis.discordbot_jvm;

import de.rwth_erstis.discordbot_jvm.commands.*;
import de.rwth_erstis.discordbot_jvm.constants.BOT;
import de.rwth_erstis.discordbot_jvm.constants.DOTENV;
import de.rwth_erstis.discordbot_jvm.core.Bot;
import de.rwth_erstis.discordbot_jvm.events.MessageCommandEvent;
import de.rwth_erstis.discordbot_jvm.events.SlashCommandCommandEvent;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class CommandHandler extends ListenerAdapter {
    private final Bot bot;
    private final HashMap<String, Command> commands = new HashMap<>();
    private final ArrayList<Command> allCommands = new ArrayList<>();
    private Guild server;

    public CommandHandler(Bot bot) {
        this.bot = bot;
        bot.getJda().addEventListener(this);
        String serverID = DOTENV.SERVER_ID.getValue();
        try {
            bot.getJda().awaitReady();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (serverID != null)
            server = bot.getJda().getGuildById(serverID);

        this.registerKnowCommands();
    }

    public void registerKnowCommands() {
        this.registerCommand(new Help(bot));
        registerCommand(new ChuckNorrisJokeCommand());
        registerCommand(new TrumpQuoteCommand());
        registerCommand(new AdviceCommand());
        registerCommand(new NewsCommand());
        registerCommand(new InsultCommand());
        registerCommand(new RhymeCommand());
    }

    private void registerCommand(Command command) {
        commands.put(command.getName().toLowerCase(), command);
        allCommands.add(command);
        for (String alias : command.getAliases()) {
            commands.put(alias.toLowerCase(), command);
        }
        CommandData data = command.getCommandData();
        if (data != null)
            registerSlashCommand(data);
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String content = event.getMessage().getContentRaw();
        if (!content.startsWith(BOT.PREFIX))
            return;
        content = content.replaceFirst(BOT.PREFIX, "");
        String cmd = content.split(" ", 2)[0];
        Command command = getCommand(cmd);
        if (command != null)
            command.run(new MessageCommandEvent(event));
    }

    @Override
    public void onSlashCommand(@NotNull SlashCommandEvent event) {
        Command command = getCommand(event.getName());
        if (command != null)
            command.run(new SlashCommandCommandEvent(event));
    }

    private void registerSlashCommand(CommandData data) {
        if (getServer() != null)
            getServer().upsertCommand(data).queue();
    }

    private Bot getBot() {
        return bot;
    }

    public Command getCommand(String name) {
        return commands.get(name.toLowerCase());
    }

    public Collection<Command> getCommands() {
        return allCommands;
    }

    public Guild getServer() {
        return server;
    }
}
