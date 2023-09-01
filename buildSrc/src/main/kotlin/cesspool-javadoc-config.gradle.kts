plugins {
    id("cesspool-java-conventions")
}

java {
    withJavadocJar()
    withSourcesJar()
}

tasks.withType<Javadoc> {
    val options = options as StandardJavadocDocletOptions
    options.links(
        "https://hub.spigotmc.org/javadocs/spigot/"
    )
}

tasks.build {
    dependsOn(tasks.javadoc)
}