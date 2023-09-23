package com.jeff_media.cesspool.nms.generic;

import org.bukkit.entity.Player;

public interface NMSAdapter {
    /**
     * Gets the NMS version package, e.g. "v1_20_R1"
     * @return NMS version package
     */
    String getNMSVersionPackage();

    /**
     * Turns a Bukkit Player into an NMS ServerPlayer
     * @param player Bukkit Player
     * @return NMS ServerPlayer
     */
    Object nms(Player player);
}
