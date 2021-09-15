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

val kotlinVersion = "1.5.21"
val slf4jVersion = "2.14.1"
val jdaVersion = "4.3.0_277"
val javaDotenvVersion = "5.2.2"
val junitJupiterVersion = "5.7.2"
val guavaVersion = "30.1.1-jre"
val gsonVersion = "2.8.8"

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

    //Gson
    implementation("com.google.code.gson", "gson", gsonVersion)

    //SLF4J
    // https://mvnrepository.com/artifact/org.apache.logging.log4j/
    implementation("org.apache.logging.log4j", "log4j-slf4j-impl", slf4jVersion)
    implementation("org.apache.logging.log4j", "log4j-api", slf4jVersion)
    implementation("org.apache.logging.log4j", "log4j-core", slf4jVersion)
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        resources.srcDir("src/main/resources")
    }
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

tasks.withType<Copy> {
    duplicatesStrategy = org.gradle.api.file.DuplicatesStrategy.INCLUDE
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