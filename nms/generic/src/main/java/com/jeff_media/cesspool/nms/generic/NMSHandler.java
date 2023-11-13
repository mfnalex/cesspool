package com.jeff_media.cesspool.nms.generic;

import com.mojang.authlib.GameProfile;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * Represents an NMS handler, that is, a class that handles all NMS stuff
 */
public interface NMSHandler {

    /**
     * Plays the totem animation for the given player
     * @param player Player
     */
    void playTotemAnimation(@NotNull Player player);

    default int getItemStackSizeInBytes(@NotNull ItemStack item) {
        return -1;
    }

    void setHeadTexture(Block block, GameProfile gameProfile);
}
