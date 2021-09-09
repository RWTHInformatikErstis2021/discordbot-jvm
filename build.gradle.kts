import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.30"
    application
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "de.rwth_erstis"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://kotlin.bintray.com/kotlinx/")
    maven("https://m2.dv8tion.net/releases/")
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.2")

    implementation("com.google.guava:guava:30.1.1-jre")

    // Discord
    implementation("net.dv8tion", "JDA", "4.3.0_277") {
        exclude(module = "opus-java")
    }

    // Util
    implementation("io.github.cdimascio", "java-dotenv", "5.2.2")

    //Kotlin
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.30")
}

application {
    mainClass.set("discordbot_jvm.LauncherKt")
    @Suppress("DEPRECATION")
    mainClassName = application.mainClass.get()
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "15"
}

tasks.withType<Jar> {
    manifest {
        attributes(
            mapOf(
                "Main-Class" to application.mainClass
            )
        )
    }
}