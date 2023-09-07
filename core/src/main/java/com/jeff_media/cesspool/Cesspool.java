package com.jeff_media.cesspool;


import com.jeff_media.cesspool.nms.aggregator.NMSHandlerFactory;
import com.jeff_media.cesspool.nms.generic.NMSHandler;
import org.bukkit.Color;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.logging.Logger;

/**
 * Main class of Cesspool. Provides access to the plugin instance and logger.
 */
public class Cesspool {

    @Nullable
    private static Plugin plugin;
    @NotNull
    private static CesspoolLogger logger = CesspoolLogger.getLogger(Cesspool.class);
    @Nullable
    private static NMSHandler nmsHandler;

    /**
     * Initializes cesspool. Must be called before using any other methods.
     * @param plugin Plugin
     */
    public static void init(@NotNull Plugin plugin) {
        Cesspool.plugin = plugin;
        CesspoolLogger.setParentLogger(plugin.getLogger());
        Cesspool.nmsHandler = NMSHandlerFactory.getNMSHandler(); // TODO: Make this configurable
    }

    /**
     * Gets the Plugin
     * @return Plugin
     */
    @NotNull
    public static Plugin plugin() {
        if (plugin != null) {
            return plugin;
        }

        return plugin = getPluginByProvidingClass();
    }

    /**
     * Gets the Plugin
     *
     * @return Plugin
     * @throws IllegalStateException when called during static initialization
     */
    private static Plugin getPluginByProvidingClass() throws IllegalStateException {
        try {
            return JavaPlugin.getProvidingPlugin(Cesspool.class);
        } catch (IllegalStateException ex) {
            // Code is called from static initializer of this plugin
            throw new IllegalStateException("Cannot get plugin instance by its class during static initialization", ex);
        }
    }

    @Nullable
    public static NMSHandler getNMSHandler() {
        return nmsHandler;
    }

}
