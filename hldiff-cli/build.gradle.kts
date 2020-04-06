plugins {
    kotlin("jvm")
    application
}

group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    implementation(project(":hldiff-core"))
    implementation("com.xenomachina:kotlin-argparser:2.0.7")
}

application {
    applicationName = "hldiff"
    mainClassName = "ru.karvozavr.hldiff.app.MainKt"
}
