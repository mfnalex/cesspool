package com.jeff_media.cesspool.gradle.tasks

import org.gradle.api.DefaultTask
import org.gradle.api.provider.Property
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.TaskAction
import java.io.File


val REGEX_DUPLICATED_LINK = "(?<firstLink><a .*?>.*?<\\/a>)\\s*\\k<firstLink>".toRegex()
val REGEX_DOUBLE_ANNOTATION = "(?<annotation>@[A-Za-z.]+)\\s+\\k<annotation>\\s+".toRegex()

/**
 * Fixes double javadoc annotations from appearing in the html files
 */
abstract class FixJavadoc : DefaultTask() {

    @get:Input
    abstract val directory: Property<File>

    fun removeDuplicatedLinks(input: String): String {
        return input.replace(REGEX_DUPLICATED_LINK, "\${firstLink}")
    }

    fun removeDuplicatedAnnotations(input: String): String {
        return input.replace(REGEX_DOUBLE_ANNOTATION, "\${annotation} ")
    }

    @TaskAction
    fun process() {
        directory.get()
            .walk()
            .filter { file -> file.name.endsWith(".html") }
            .forEach { file ->
                run {
                    var content = file.readText()
                    content = removeDuplicatedLinks(content)
                    content = removeDuplicatedAnnotations(content)
                    file.writeText(content)
                }
        }
    }
}