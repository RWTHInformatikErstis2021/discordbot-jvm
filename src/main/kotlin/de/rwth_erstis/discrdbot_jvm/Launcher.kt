package de.rwth_erstis.discrdbot_jvm

import de.rwth_erstis.discrdbot_jvm.Test
import io.github.cdimascio.dotenv.dotenv
import kotlin.system.exitProcess
import de.rwth_erstis.discrdbot_jvm.core.BotImpl as Bot


fun main(args: Array<String>) {
    print(Test().test())
    val env = dotenv()
    val token = env["TOKEN"] ?: exitProcess(1)
    val bot = Bot(token)
}
