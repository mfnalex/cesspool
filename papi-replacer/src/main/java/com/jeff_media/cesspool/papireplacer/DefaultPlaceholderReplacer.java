package com.jeff_media.cesspool.papireplacer;

import me.clip.placeholderapi.PlaceholderAPIPlugin;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

class DefaultPlaceholderReplacer {

    private static final Map<@NotNull String, @NotNull Function<@NotNull OfflinePlayer, @Nullable String>> defaultPlayerRequiredPlaceholders = new HashMap<>();
    private static final Map<@NotNull String, @NotNull Function<@Nullable OfflinePlayer, @Nullable String>> defaultPlayerOptionalPlaceholders = new HashMap<>();

    static {
        addPlayer("player_name", OfflinePlayer::getName);
        addPlayer("player_uuid", p -> p.getUniqueId().toString());
    }

    private static void addPlayer(@NotNull String placeholder, @NotNull Function<@NotNull OfflinePlayer, @Nullable String> function) {
        defaultPlayerRequiredPlaceholders.put("%" + placeholder + "%", function);
    }

    private static void addNonPlayer(@NotNull String placeholder, @NotNull Function<@Nullable OfflinePlayer, @Nullable String> function) {
        defaultPlayerOptionalPlaceholders.put("%" + placeholder + "%", function);
    }

    @NotNull
    static String apply(@NotNull String string, @Nullable OfflinePlayer player) {

        string = doReplace(string, player, defaultPlayerOptionalPlaceholders);

        if(player == null) {
            return string;
        }

        string = doReplace(string, player, defaultPlayerRequiredPlaceholders);

        return string;
    }

    private static @NotNull String doReplace(@NotNull String string, @Nullable OfflinePlayer player, Map<String, Function<OfflinePlayer, String>> placeholders) {
        for(Map.Entry<String, Function<OfflinePlayer, String>> entry : placeholders.entrySet()) {
            String placeholder = entry.getKey();
            if(string.contains(placeholder)) {
                String replacement = entry.getValue().apply(player);
                if(replacement != null) {
                    string = string.replace(placeholder, replacement);
                }
            }
        }
        return string;
    }

//    private static String bool(boolean b) {
//        if(PapiReplacer.isPapiEnabled()) {
//            return b ? PlaceholderAPIPlugin.booleanTrue() : PlaceholderAPIPlugin.booleanFalse();
//        }
//
//        return b ? "yes" : "no";
//    }

}
