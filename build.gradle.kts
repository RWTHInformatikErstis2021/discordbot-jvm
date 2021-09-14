plugins {
    application
    java
    kotlin("jvm") version ("1.5.30")
    id("com.github.johnrengelman.shadow") version ("7.0.0")
}

group = "de.rwth_erstis"
version = "0.1-SNAPSHOT"

repositories {
    mavenCentral()
    mavenLocal()
    maven("https://kotlin.bintray.com/kotlinx/")
    maven("https://m2.dv8tion.net/releases/")
}

val kotlinTestVersion = "3.4.2"
val kotlinVersion = "1.5.21"
val slf4jVersion = "2.0.0-alpha5"
val jdaVersion = "4.3.0_277"
val javaDotenvVersion = "5.2.2"
val junitJupiterVersion = "5.7.2"
val guavaVersion = "30.1.1-jre"

val jvmVersion = 15

dependencies {
    testImplementation("org.junit.jupiter", "junit-jupiter", junitJupiterVersion)
    implementation("com.google.guava", "guava", guavaVersion)

    // Discord
    implementation("net.dv8tion", "JDA", jdaVersion) {
        exclude(module = "opus-java")
    }

    // Util
    implementation("io.github.cdimascio", "java-dotenv", javaDotenvVersion)

    //Kotlin
    implementation("org.jetbrains.kotlin", "kotlin-stdlib", kotlinVersion)

    //Tests
    //testCompile("io.kotlintest", "kotlintest-runner-junit5", kotlinTestVersion)

    //SLF4J
    implementation("org.slf4j", "slf4j-nop", slf4jVersion)
}

tasks.test {
    useJUnitPlatform()
}

java {
    sourceSets["main"].apply {
        java.srcDir("src/main/java")
    }
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(jvmVersion))
    }
}

kotlin {
    sourceSets["main"].apply {
        kotlin.srcDir("src/main/myKotlin")
    }
}

application {
    mainClass.set("de.rwth_erstis.discordbot_jvm.LauncherKt")
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions {
        jvmTarget = jvmVersion.toString()
    }
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