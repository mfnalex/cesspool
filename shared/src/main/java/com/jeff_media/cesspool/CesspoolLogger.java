package com.jeff_media.cesspool;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CesspoolLogger {

    @NotNull
    private static Logger parentLogger = Bukkit.getLogger();

    @NotNull
    private final String className;

    private CesspoolLogger(@NotNull final Class<?> owningClazz) {
        this.className = owningClazz.getName();
    }

    public static void setParentLogger(@NotNull final Logger parentLogger) {
        CesspoolLogger.parentLogger = parentLogger;
    }

    public static CesspoolLogger getLogger(@NotNull final Class<?> clazz) {
        return new CesspoolLogger(clazz);
    }

    private String prefix(@NotNull final String message) {
        return "[" + className + "] " + message;
    }

    public void info(@NotNull final String message) {
        parentLogger.info(prefix(message));
    }

    public void warning(@NotNull final String message) {
        parentLogger.warning(prefix(message));
    }

    public void warning(@NotNull final String message, @NotNull final Throwable throwable) {
        parentLogger.log(Level.WARNING, prefix(message), throwable);
    }

    public void severe(@NotNull final String message) {
        parentLogger.severe(prefix(message));
    }

    public void severe(@NotNull final String message, @NotNull final Throwable throwable) {
        parentLogger.log(Level.SEVERE, prefix(message), throwable);
    }

    public void log(@NotNull Level level, @NotNull final String message, @NotNull final Throwable throwable) {
        parentLogger.log(level, prefix(message), throwable);
    }


}
