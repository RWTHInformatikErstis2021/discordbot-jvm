package de.rwth_erstis.discordbot_jvm.core

import de.rwth_erstis.discordbot_jvm.CommandHandler
import de.rwth_erstis.discordbot_jvm.constants.BOT.ACTIVITY
import de.rwth_erstis.discordbot_jvm.constants.BOT.GATEWAY_INTENTS
import de.rwth_erstis.discordbot_jvm.constants.BOT.STATUS
import de.rwth_erstis.discordbot_jvm.constants.DOTENV
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

internal class BotImpl() : Bot {
    override val jda: JDA = JDABuilder.create(
        DOTENV.TOKEN.value,
        GatewayIntent.getIntents(GATEWAY_INTENTS)
    )
        .setActivity(ACTIVITY)
        .setStatus(STATUS)
        .build()

    override val cmdHandler: CommandHandler = CommandHandler(this)
}
