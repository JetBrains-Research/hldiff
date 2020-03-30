plugins {
    kotlin("jvm") version "1.3.70"
    application
}

group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    compile(project(":hldiff-core"))
    compile("com.xenomachina:kotlin-argparser:2.0.7")
}

tasks {
    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }
}

application {
    applicationName = "hldiff"
    mainClassName = "ru.karvozavr.hldiff.app.MainKt"
}