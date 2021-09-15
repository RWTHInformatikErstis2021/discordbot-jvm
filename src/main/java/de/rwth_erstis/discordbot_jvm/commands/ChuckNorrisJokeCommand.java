package de.rwth_erstis.discordbot_jvm.commands;

import de.rwth_erstis.discordbot_jvm.apis.ChuckNorrisJokesApi;
import de.rwth_erstis.discordbot_jvm.events.CommandEvent;

public class ChuckNorrisJokeCommand extends Command{
    public ChuckNorrisJokeCommand() {
        super("chuckNorrisJoke", "Sends a Chuck Norris joke", "joke");
    }

    @Override
    public void run(CommandEvent event) {
        event.reply(ChuckNorrisJokesApi.getInstance().getObject());
    }
}
