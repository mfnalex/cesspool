plugins {
    `java-library`
    `maven-publish`
}

group = "com.jeff-media.cesspool"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.jetbrains:annotations:24.0.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    /*toolchain {
        languageVersion.set(JavaLanguageVersion.of(8))
    }*/
}

tasks.named<Test>("test") {
    useJUnitPlatform()

    testLogging {
        events("passed", "skipped", "failed")
    }
}

tasks.withType<Javadoc> {

}

//tasks.javadoc {
//    source = sourceSets["main"].allJava
//}
//
//tasks.build {
//    dependsOn(tasks.javadoc)
//}

publishing {
    publications {
        create<MavenPublication>("artifact") {
            from(components["java"])
        }
    }
}

tasks.withType<Javadoc> {
    val options = options as StandardJavadocDocletOptions
    options.links(
        "https://hub.spigotmc.org/javadocs/spigot/"
    )
}