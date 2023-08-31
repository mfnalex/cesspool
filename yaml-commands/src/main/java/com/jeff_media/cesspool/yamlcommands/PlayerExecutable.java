package com.jeff_media.cesspool.yamlcommands;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

interface PlayerExecutable {
    boolean run(@Nullable Player player) throws IllegalArgumentException;
}
