package de.rwth_erstis.discrdbot_jvm.core

import de.rwth_erstis.discrdbot_jvm.constants.BOT.ACTIVITY
import de.rwth_erstis.discrdbot_jvm.constants.BOT.GATEWAY_INTENTS
import de.rwth_erstis.discrdbot_jvm.constants.BOT.STATUS
import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import net.dv8tion.jda.api.requests.GatewayIntent

internal class BotImpl(token: String) : Bot {
    override val jda: JDA = JDABuilder.create(
        token,
        GatewayIntent.getIntents(GATEWAY_INTENTS)
    )
        .setActivity(ACTIVITY)
        .setStatus(STATUS)
        .build()
}
