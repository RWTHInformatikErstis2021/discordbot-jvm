package de.rwth_erstis.discordbot_jvm.constants

import io.github.cdimascio.dotenv.dotenv
import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.requests.GatewayIntent

object BOT {
    const val PREFIX = "!"
    val STATUS = OnlineStatus.ONLINE
    val ACTIVITY = Activity.listening("$PREFIX help")
    val GATEWAY_INTENTS = GatewayIntent.ALL_INTENTS
    val SERVER_ID = dotenv()["SERVER_ID"]
}
