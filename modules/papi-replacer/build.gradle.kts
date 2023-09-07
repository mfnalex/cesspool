plugins {
    id("cesspool-lib-module")
}

repositories {
    maven {
        name = "extendedclip"
        url = uri("https://repo.extendedclip.com/content/repositories/placeholderapi/")
    }
}

dependencies {
    compileOnly("me.clip:placeholderapi:2.11.3")
}