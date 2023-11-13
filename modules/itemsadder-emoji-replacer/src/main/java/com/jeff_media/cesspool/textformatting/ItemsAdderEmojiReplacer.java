package com.jeff_media.cesspool.textformatting;

import com.jeff_media.cesspool.reflection.ReflectionUtils;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff_media.cesspool.Validate.*;

/**
 * Replaces ItemsAdder emojis in strings
 */
public final class ItemsAdderEmojiReplacer {

    private ItemsAdderEmojiReplacer() { }

    private static Boolean itemsAdderInstalled = null;

    /**
     * Checks if ItemsAdder is installed and if it has the required classes
     * @return True if ItemsAdder is installed and has the required classes, false otherwise
     */
    public static boolean hasItemsAdder() {
        if(itemsAdderInstalled != null && itemsAdderInstalled) return true;
        if(itemsAdderInstalled != null) return false;

        if(Bukkit.getPluginManager().getPlugin("ItemsAdder") == null) {
            itemsAdderInstalled = false;
            return false;
        }

        if(ReflectionUtils.getClass("dev.lone.itemsadder.api.FontImages.FontImageWrapper") == null) {
            itemsAdderInstalled = false;
            return false;
        }

        return true;
    }

    /**
     * Replaces ItemsAdder emojis in the given input string
     * @param player Player to replace emojis for. Can be null
     * @param input Input string
     * @return Input string with emojis replaced
     */
    public static String replaceEmojis(@Nullable Player player, @NotNull String input) {
        paramNotNull(input, "input");

        if(!hasItemsAdder()) return input;
        if(player != null) {
            return FontImageWrapper.replaceFontImages(player, input);
        } else {
            return FontImageWrapper.replaceFontImages(input);
        }
    }
}
