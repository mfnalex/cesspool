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
        "https://hub.spigotmc.org/javadocs/spigot/",
        "https://javadoc.io/doc/org.jetbrains/annotations/24.0.1"
    )
}