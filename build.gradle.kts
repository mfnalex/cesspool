allprojects {
    repositories {
        mavenCentral()
        gradlePluginPortal()

        maven {
            name = "spigotmc"
            url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
        }

        maven {
            name = "extendedclip"
            url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
        }
    }
}