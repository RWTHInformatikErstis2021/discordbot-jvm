package de.rwth_erstis.discordbot_jvm.core

import de.rwth_erstis.discordbot_jvm.CommandHandler
import net.dv8tion.jda.api.JDA

interface Bot {
    val jda: JDA

    val cmdHandler: CommandHandler
}
