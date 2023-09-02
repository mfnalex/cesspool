package com.jeff_media.cesspool.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.Objects;
import java.util.logging.Level;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

/**
 * Represents a configuration that automatically loads the default values from the plugin's resources, if it exists.
 * If no matching resource is found, the default configuration is empty. Automatically creates the file in the
 * plugin's datafolder if it doesn't exist, otherwise it loads the already saved file.
 */
public class Config extends YamlConfiguration {

    private final String filename;
    private final File file;
    private final Plugin plugin;

    /**
     * Creates a new config with the given filename.
     *
     * @param plugin   the plugin that owns this config
     * @param fileName the file name of this config
     */
    public Config(@NotNull final Plugin plugin, @NotNull final String fileName) {
        this.plugin = notNull(plugin, "plugin");
        this.filename = notNull(fileName, "fileName");
        file = new File(plugin.getDataFolder(), fileName);
        loadDefaults();
        reload();
    }

    private void loadDefaults() {
        final YamlConfiguration defaultConfig = new YamlConfiguration();

        try (final InputStream inputStream = plugin.getResource(filename)) {
            if (inputStream != null) {
                try (final Reader reader = new InputStreamReader(Objects.requireNonNull(inputStream))) {
                    defaultConfig.load(reader);
                }
            }
        } catch (final IOException exception) {
            throw new IllegalArgumentException("Could not load included config file " + filename, exception);
        } catch (final InvalidConfigurationException exception) {
            throw new IllegalArgumentException("Invalid default config for " + filename, exception);
        }

        setDefaults(defaultConfig);
    }

    /**
     * Reloads the configuration
     */
    public void reload() {
        saveDefaultConfig();
        try {
            load(file);
        } catch (final IOException exception) {
            new IllegalArgumentException("Could not find or load file " + filename, exception).printStackTrace();
        } catch (final InvalidConfigurationException exception) {
            plugin.getLogger().log(Level.WARNING, "Your config file " + filename + " is invalid, using default values now. Please fix the below mentioned errors and try again.", exception);
        }
    }

    private void saveDefaultConfig() {
        if (!file.exists()) {
            File parent = file.getParentFile();
            if (parent != null && !parent.exists() && !parent.mkdirs()) {
                throw new UncheckedIOException(new IOException("Could not create directory " + parent.getAbsolutePath()));

            }
            plugin.saveResource(filename, false);
        }
    }

    /**
     * Saves the configuration under its original file name
     *
     * @throws IOException if the underlying YamlConfiguration throws it
     */
    public void save() throws IOException {
        this.save(file);
    }

    /**
     * Saves the configuration under the given file name
     *
     * @throws IOException if the underlying YamlConfiguration throws it
     */
    public void save(@NotNull final File file) throws IOException {
        try {
            super.save(file);
        } catch (IOException exception) {
            throw new UncheckedIOException(exception);
        }
    }

    /**
     * Returns the file name of this config
     *
     * @return the file name of this config
     */
    @NotNull
    public String getFileName() {
        return filename;
    }

    /**
     * Returns the file of this config
     *
     * @return the file of this config
     */
    @NotNull
    public File getFile() {
        return file;
    }

}

