plugins {
    id("cesspool-lib-module")
}

repositories {
    maven {
        name = "jitpack-itemsadder"
        url = uri("https://jitpack.io")
    }
}

dependencies {
    compileOnly("com.github.LoneDev6:API-ItemsAdder:3.6.1")
}