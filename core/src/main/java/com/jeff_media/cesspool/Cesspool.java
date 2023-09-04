package com.jeff_media.cesspool;


import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.logging.Logger;

/**
 * Main class of Cesspool. Provides access to the plugin instance and logger.
 */
public class Cesspool {

    private static Plugin plugin;
    private static Logger logger;

    /**
     * Initializes cesspool. Must be called before using any other methods.
     * @param plugin Plugin
     */
    public static void init(Plugin plugin) {
        Cesspool.plugin = plugin;
        Cesspool.logger = plugin.getLogger();
    }

    /**
     * Gets the Plugin
     * @return Plugin
     */
    public static Plugin plugin() {
        if (plugin != null) {
            return plugin;
        }

        return plugin = getFromPluginFromJavaPluginByClass();
    }

    /**
     * Gets the Logger
     * @return Logger
     */
    public static Logger logger() {
        if(logger != null) {
            return logger;
        }
        return logger = plugin.getLogger();
    }

    /**
     * Gets the Plugin
     *
     * @return Plugin
     * @throws IllegalStateException when called during static initialization
     */
    private static Plugin getFromPluginFromJavaPluginByClass() throws IllegalStateException {
        try {
            return JavaPlugin.getProvidingPlugin(Cesspool.class);
        } catch (IllegalStateException ex) {
            // Code is called from static initializer of this plugin
            throw new IllegalStateException("Cannot get plugin instance by its class during static initialization", ex);
        }
    }

}
