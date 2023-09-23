package com.jeff_media.cesspool;

import com.jeff_media.cesspool.reflection.ReflectionUtils;
import dev.lone.itemsadder.api.FontImages.FontImageWrapper;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

public final class ItemsAdderEmojiReplacer {

    private ItemsAdderEmojiReplacer() { }

    private static Boolean itemsAdderInstalled = null;

    public boolean hasItemsAdder() {
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

    public static String replaceEmojis(@Nullable Player player, @NotNull String input) {
        notNull(input, "input");

        if(!hasItemsAdder()) return input;
        if(player != null) {
            return FontImageWrapper.replaceFontImages(player, input);
        } else {
            return FontImageWrapper.replaceFontImages(input);
        }
    }
}
