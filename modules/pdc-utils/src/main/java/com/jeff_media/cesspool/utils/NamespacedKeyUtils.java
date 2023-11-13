package com.jeff_media.cesspool.utils;

import com.jeff_media.cesspool.Cesspool;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.jeff_media.cesspool.Validate.*;

/**
 * Utility methods to work with NamespacedKeys
 */
public class NamespacedKeyUtils {

    private static final Map<String, NamespacedKey> CACHED_NAMESPACED_KEYS = new HashMap<>();

    /**
     * Gets a NamespacedKey from a String. This method caches the NamespacedKey, so it's safe to call it multiple times with the same key.
     * @param key Key
     * @return NamespacedKey
     */
    @NotNull
    public static NamespacedKey getKey(@NotNull final String key) {
        paramNotNull(key, "key");
        return CACHED_NAMESPACED_KEYS.computeIfAbsent(key,
                (__) -> new NamespacedKey(Cesspool.plugin(), key));
    }

    /**
     * Gets the namespace of the current plugin
     * @return Namespace
     */
    @NotNull
    public static String getNamespaced() {
        return getKey("test").getNamespace();
    }
}
