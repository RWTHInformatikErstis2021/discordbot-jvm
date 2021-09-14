package de.rwth_erstis.discordbot_jvm

import de.rwth_erstis.discordbot_jvm.constants.DOTENV
import de.rwth_erstis.discordbot_jvm.core.BotImpl as Bot

class Launcher {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            DOTENV.checkValuesValid()
            Bot()
        }
    }
}