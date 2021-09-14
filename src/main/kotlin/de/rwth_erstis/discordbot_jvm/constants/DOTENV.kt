package de.rwth_erstis.discordbot_jvm.constants

import de.rwth_erstis.discordbot_jvm.utils.Checks
import io.github.cdimascio.dotenv.dotenv
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.system.exitProcess


enum class DOTENV(private val envName: String, val critical: Boolean, val validValue: (input: String?) -> Boolean) {
    TOKEN("TOKEN", true, Checks()::isValidToken),
    SERVER_ID("SERVER_ID", true, Checks()::isValidSnowflake);

    val value: String? = dotenv()[this.envName]

    companion object {
        private val logger: Logger = LoggerFactory.getLogger(this::class.java)

        fun checkValuesValid() {
            values().forEach {
                if (!it.validValue.invoke(it.value)) {
                    logger.warn("${it.name} has no valid value in the env.")
                    if (it.critical) {
                        logger.error("${it.name} is critical for the program to launch. Exiting the process.")
                        exitProcess(1)
                    }
                }
            }
        }
    }
}