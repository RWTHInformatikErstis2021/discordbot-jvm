package de.rwth_erstis.discordbot_jvm.utils

import net.dv8tion.jda.api.JDA
import net.dv8tion.jda.api.JDABuilder
import java.time.OffsetDateTime
import java.util.*
import javax.security.auth.login.LoginException


class Checks {
    fun isValidToken(value: String?): Boolean {
        if (value == null) return false
        return try {
            val jda: JDA = JDABuilder.createDefault(value).build()
            jda.shutdown()
            true
        } catch (e: LoginException) {
            false
        }
    }

    fun isValidSnowflake(value: String?): Boolean {
        if (value == null) return false

        try {
            value.toLong()
        } catch (e: NumberFormatException) {
            return false
        }

        val timestampOffset = 22
        val discordEpoch = 1420070400000L

        val timestamp: Long = (value.toLong() ushr timestampOffset) + discordEpoch
        val gmt = Calendar.getInstance(TimeZone.getTimeZone("GMT"))
        gmt.timeInMillis = timestamp

        return OffsetDateTime.ofInstant(gmt.toInstant(), gmt.timeZone.toZoneId()).isBefore(OffsetDateTime.now())
    }
}