plugins {
    java
    kotlin("jvm") version "1.3.70"
    kotlin("plugin.serialization") version "1.3.70"
    `java-library`
}

group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-runtime:0.20.0")
    testCompile("junit", "junit", "4.12")
    compile(fileTree("include" to listOf("*.jar"), "dir" to "libs"))
    compile(project(":python-gen"))
    implementation("com.google.code.gson:gson:2.8.6")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

