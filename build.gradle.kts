import kr.entree.spigradle.kotlin.papermc
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.7.0"
    id("com.diffplug.spotless") version "6.8.0"
    id("kr.entree.spigradle") version "2.4.2"
}

group = "com.dumbdogdiner"
version = "1.0.0"

allprojects {
    // Declare global repositories
    repositories {
        mavenCentral()

        // Add paper repository here, as it's used in both API and Bukkit modules.
        papermc()
    }
}

@Suppress("UnstableApiUsage")
subprojects {
    group = "com.dumbdogdiner.myawesomeplugin"

    apply(plugin = "java")
    apply(plugin = "kotlin")
    apply(plugin = "kr.entree.spigradle")

    // Spotless configuration
    apply(plugin =  "com.diffplug.spotless")
    spotless {
        ratchetFrom = "origin/master"
    }

    repositories {
        mavenCentral()
    }

    tasks.withType<JavaCompile> {
        targetCompatibility = JavaVersion.VERSION_17.toString()
        sourceCompatibility = JavaVersion.VERSION_17.toString()
    }

    tasks.withType<KotlinCompile> {
        kotlinOptions.jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

tasks {
    // Disable root project building spigot description.
    generateSpigotDescription {
        enabled = false
    }
}
