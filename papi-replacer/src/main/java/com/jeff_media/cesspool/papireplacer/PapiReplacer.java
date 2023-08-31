package com.jeff_media.cesspool.papireplacer;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

/**
 * Applies PlaceholderAPI placeholders
 */
public class PapiReplacer {

    private static boolean isPapiEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    @NotNull
    private static String doApply(@NotNull String string, @Nullable OfflinePlayer player) {
        return PlaceholderAPI.setPlaceholders(player, string);
    }

    @NotNull
    private static List<@NotNull String> doApply(@NotNull List<@NotNull String> strings, @Nullable OfflinePlayer player) {
        return strings.stream().map(s -> doApply(s, player)).toList();
    }

    /**
     * Applies PlaceholderAPI placeholders to a string
     * @param string String to apply placeholders to
     * @param player Player to apply placeholders for
     * @return String with placeholders applied
     */
    @NotNull
    @Contract(pure = true, value = "_, _ -> new")
    public static String apply(@NotNull String string, @Nullable OfflinePlayer player) {
        if (isPapiEnabled()) {
            return doApply(string, player);
        } else {
            return string;
        }
    }

    /**
     * Applies PlaceholderAPI placeholders to a list of strings
     * @param strings List of strings to apply placeholders to
     * @param player Player to apply placeholders for
     * @return Unmodifiable list of strings with placeholders applied
     */
    @NotNull
    @Contract(pure = true, value = "_, _ -> new")
    public static List<@NotNull String> apply(@NotNull List<@NotNull String> strings, @Nullable OfflinePlayer player) {
        if (isPapiEnabled()) {
            return doApply(strings, player);
        } else {
            return Collections.unmodifiableList(strings);
        }
    }

}
