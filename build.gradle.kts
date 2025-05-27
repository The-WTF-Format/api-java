plugins {
    id("java")
    `java-library`
}

allprojects {
    apply(plugin = "java")
    apply(plugin = "java-library")

    group = "wtf.file"
    version = "1.1.1"

    repositories {
        mavenCentral()
    }
}

tasks.jar {
    subprojects.forEach { sub ->
        dependsOn(sub.tasks.jar)
    }

    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
}

dependencies {
    api(project(":api"))
    api(project(":impl"))
}
