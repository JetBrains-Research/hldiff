plugins {
    java
    kotlin("jvm")
    kotlin("plugin.serialization") version "1.3.70"
    `java-library`
    id("com.github.johnrengelman.shadow") version "5.2.0"
}

group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

dependencies {
    compile(kotlin("stdlib"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    testCompile("junit", "junit", "4.12")
    compile(fileTree("include" to listOf("*.jar"), "dir" to "libs"))
    compile(project(":python-gen"))
    implementation("com.google.code.gson:gson:2.8.6")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}
