import org.apache.tools.ant.taskdefs.Java

plugins {
    `java-library`
    `maven-publish`
}

/**
 * Returns a proper group ID for this subproject, e.g. "com.jeff-media.cesspool.nms" for nms/generic
 */
fun getGroupId(): String {
    val groupId = "com.jeff-media.cesspool"
    val myParentDir = projectDir.parentFile
    if (rootDir == myParentDir) {
        return groupId
    }
    val relativeGroupId = myParentDir.relativeTo(rootDir).path.replace(File.separatorChar, '.')
    if (relativeGroupId.isEmpty()) {
        throw RuntimeException("Couldn't determine relative group ID for $projectDir")
    }
    return "${groupId}.${relativeGroupId}"
}

group = getGroupId()
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
    if (JavaVersion.current() < JavaVersion.VERSION_17) {
        throw RuntimeException("Java 17 or higher is required to build cesspool.")
    }
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
        }
    }
}

tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
}