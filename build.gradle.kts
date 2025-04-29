plugins {
    id("java")
}

allprojects {
    apply(plugin = "java")

    group = "wtf.file"
    version = "1.0-SNAPSHOT"

    repositories {
        mavenCentral()
    }
}