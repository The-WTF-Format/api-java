plugins {
    id("java")
    `java-library`
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")

    group = "wtf.file"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}

dependencies {
    api(project(":api"))
    api(project(":impl"))
}