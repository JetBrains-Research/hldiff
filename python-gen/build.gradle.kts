plugins {
    java
}

group = "com.github"
version = "1.0-SNAPSHOT"

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(files("../hldiff-core/libs/core-2.1.2.jar"))
    testImplementation("junit", "junit", "4.12")
}
