plugins {
    java
    kotlin("jvm") version "1.3.60"
    application
}

group = "ru.karvozavr"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    testCompile("junit", "junit", "4.12")
    compile(fileTree("include" to listOf("*.jar"), "dir" to "libs"))
    compile(project(":python-gen"))
    compile("com.xenomachina:kotlin-argparser:2.0.7")
    implementation( "com.google.code.gson:gson:2.8.6")
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

application {
    applicationName = "hldiff"
    mainClassName = "ru.karvozavr.hldiff.app.MainKt"
}