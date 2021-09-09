package de.rwth_erstis.discordbot_jvm.core

import net.dv8tion.jda.api.JDA

interface Bot {
    val jda: JDA
}
