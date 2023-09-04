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
    // implementation = <scope>runtime</scope>
    // api = <scope>compile</scope>
    // compileOnlyApi = <scope>compile</scope>
    // compileOnly = doesn't appear in pom at all
    compileOnly("org.jetbrains:annotations:24.0.1")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
    if(JavaVersion.current() < JavaVersion.VERSION_17) {
        throw RuntimeException("Java 17 or higher is required")
    }
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

publishing {
    publications {
        create<MavenPublication>("artifact") {
            from(components["java"])
//            from(components["javadocJar"])
//            from(components["sourcesJar"])
        }
    }
}