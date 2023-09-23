package com.jeff_media.cesspool.utils;

import com.jeff_media.cesspool.Cesspool;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

public class NamespacedKeyUtils {

    private static final Map<String, NamespacedKey> CACHED_NAMESPACED_KEYS = new HashMap<>();

    @NotNull
    public static NamespacedKey getKey(@NotNull final String key) {
        notNull(key, "key");
        return CACHED_NAMESPACED_KEYS.computeIfAbsent(key,
                (__) -> new NamespacedKey(Cesspool.plugin(), key));
    }
}
