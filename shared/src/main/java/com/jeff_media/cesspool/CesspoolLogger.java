package com.jeff_media.cesspool;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Global Cesspool Logger. Uses the plugin logger if possible, otherwise falls back to the global Bukkit logger
 */
public class CesspoolLogger {

    @NotNull
    private static Logger parentLogger = Bukkit.getLogger();

    @NotNull
    private final String className;

    private CesspoolLogger(@NotNull final Class<?> owningClazz) {
        this.className = owningClazz.getName();
    }

    /**
     * Sets the parent logger. This is the logger that will be used by all CesspoolLoggers.
     * @param parentLogger Parent logger
     */
    public static void setParentLogger(@NotNull final Logger parentLogger) {
        CesspoolLogger.parentLogger = parentLogger;
    }

    /**
     * Gets a CesspoolLogger for the given class
     * @param clazz Class
     * @return CesspoolLogger
     */
    @NotNull
    public static CesspoolLogger getLogger(@NotNull final Class<?> clazz) {
        return new CesspoolLogger(clazz);
    }

    private String prefix(@NotNull final String message) {
        return "[" + className + "] " + message;
    }

    /**
     * Logs a message at the INFO level
     * @param message Message
     */
    public void info(@NotNull final String message) {
        parentLogger.info(prefix(message));
    }

    /**
     * Logs a message at the WARNING level
     * @param message Message
     */
    public void warning(@NotNull final String message) {
        parentLogger.warning(prefix(message));
    }

    /**
     * Logs a message at the WARNING level
     * @param message Message
     * @param throwable Throwable
     */
    public void warning(@NotNull final String message, @NotNull final Throwable throwable) {
        parentLogger.log(Level.WARNING, prefix(message), throwable);
    }

    /**
     * Logs a message at the SEVERE level
     * @param message Message
     */
    public void severe(@NotNull final String message) {
        parentLogger.severe(prefix(message));
    }

    /**
     * Logs a message at the SEVERE level
     * @param message Message
     * @param throwable Throwable
     */
    public void severe(@NotNull final String message, @NotNull final Throwable throwable) {
        parentLogger.log(Level.SEVERE, prefix(message), throwable);
    }

    /**
     * Logs a message at the given level
     * @param level Level
     * @param message Message
     * @param throwable Throwable
     */
    public void log(@NotNull Level level, @NotNull final String message, @NotNull final Throwable throwable) {
        parentLogger.log(level, prefix(message), throwable);
    }


}
