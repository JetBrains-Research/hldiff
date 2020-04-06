plugins {
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.3.70"
    `java-library`
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

dependencies {
    implementation(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    api(project(":python-gen"))
    api("com.google.code.gson:gson:2.8.6")
    api(fileTree("include" to listOf("*.jar"), "dir" to "libs"))
    testImplementation("junit", "junit", "4.12")
}
