package de.rwth_erstis.discrdbot_jvm.core

import net.dv8tion.jda.internal.JDAImpl

interface Bot {
    val jda: JDAImpl
}