plugins {
    java
}

repositories {
    maven {
        name = "minecraft-repo"
        url = uri("https://libraries.minecraft.net/")
    }
}

dependencies {
    compileOnly("com.mojang:authlib:1.5.21")
}