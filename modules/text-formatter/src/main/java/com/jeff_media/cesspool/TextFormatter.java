package com.jeff_media.cesspool;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class TextFormatter {

    public static String format(final @Nullable OfflinePlayer player, final @NotNull String input) {
        String text = input;

        Player onlinePlayer = player == null ? null : player.getPlayer();

        text = ItemsAdderEmojiReplacer.replaceEmojis(onlinePlayer, text);
        text = PapiReplacer.apply(text, player);
        text = colorize(text);

        return text;
    }

    private static String colorize(String text) {

    }

}
