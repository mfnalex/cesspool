plugins {
    id("cesspool-lib-module")
}

dependencies {
    api(project(":papi-replacer"))
    api(project(":itemsadder-emoji-replacer"))
//    implementation("net.kyori:adventure-text-minimessage:4.14.0") // TODO: Add support for mixed color codes and minimessage
}