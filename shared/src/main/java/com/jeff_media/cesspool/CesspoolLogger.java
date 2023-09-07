package com.jeff_media.cesspool;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

public class CesspoolLogger {

    @NotNull
    private static Logger parentLogger = Bukkit.getLogger();

    @Nullable
    private String className = null;

    private CesspoolLogger(@NotNull final Class<?> owningClazz) {
        this.className = owningClazz.getName();
    }

    public static void setParentLogger(@NotNull final Logger parentLogger) {
        CesspoolLogger.parentLogger = parentLogger;
    }

    public static CesspoolLogger getLogger(@NotNull final Class<?> clazz) {
        return new CesspoolLogger(clazz);
    }


}
