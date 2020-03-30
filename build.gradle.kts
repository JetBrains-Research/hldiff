group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

plugins {
    kotlin("jvm") version "1.3.70" apply false
}

repositories {
    mavenCentral()
}

allprojects {
    repositories {
        mavenCentral()
        jcenter()
    }
}
