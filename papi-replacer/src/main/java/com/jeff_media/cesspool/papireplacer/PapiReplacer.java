package com.jeff_media.cesspool.papireplacer;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Applies PlaceholderAPI placeholders
 */
public class PapiReplacer {

    static boolean isPapiEnabled() {
        return Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI");
    }

    @NotNull
    private static String doApplyPapi(@NotNull String string, @Nullable OfflinePlayer player) {
        return PlaceholderAPI.setPlaceholders(player, doApplyDefaults(string, player));
    }

    @NotNull
    private static List<@NotNull String> doApplyPapi(@NotNull List<@NotNull String> strings, @Nullable OfflinePlayer player) {
        return strings.stream().map(s -> doApplyPapi(s, player)).collect(Collectors.toList());
    }

    @NotNull
    private static String doApplyDefaults(@NotNull String string, @Nullable OfflinePlayer player) {
        return DefaultPlaceholderReplacer.apply(string, player);
    }

    @NotNull
    private static List<@NotNull String> doApplyDefaults(@NotNull List<@NotNull String> strings, @Nullable OfflinePlayer player) {
        return strings.stream().map(s -> doApplyDefaults(s, player)).collect(Collectors.toList());
    }

    /**
     * Applies PlaceholderAPI placeholders to a string
     *
     * @param string String to apply placeholders to
     * @param player Player to apply placeholders for
     * @return String with placeholders applied
     */
    @NotNull
    @Contract(pure = true, value = "_, _ -> new")
    public static String apply(@NotNull String string, @Nullable OfflinePlayer player) {
        if (isPapiEnabled()) {
            return doApplyPapi(string, player);
        } else {
            return doApplyDefaults(string, player);
        }
    }

    /**
     * Applies PlaceholderAPI placeholders to a list of strings
     *
     * @param strings List of strings to apply placeholders to
     * @param player  Player to apply placeholders for
     * @return Unmodifiable list of strings with placeholders applied
     */
    @NotNull
    @Contract(pure = true, value = "_, _ -> new")
    public static List<@NotNull String> apply(@NotNull List<@NotNull String> strings, @Nullable OfflinePlayer player) {
        if (isPapiEnabled()) {
            return doApplyPapi(strings, player);
        } else {
            return doApplyDefaults(strings, player);
        }
    }

}
