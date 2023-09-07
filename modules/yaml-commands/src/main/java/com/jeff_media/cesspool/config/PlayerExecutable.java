package com.jeff_media.cesspool.config;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

interface PlayerExecutable {
    boolean run(@Nullable Player player) throws IllegalArgumentException;
}
