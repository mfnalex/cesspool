package com.jeff_media.cesspool;

import com.jeff_media.cesspool.reflection.ConstantsCache;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

public final class PDCUtils {

    private PDCUtils() { }

    private static final PersistentDataType<?, ?>[] PRIMITIVE_DATA_TYPES = new ConstantsCache<>(PersistentDataType.class,
                    PersistentDataType.class,
                    (type) -> type instanceof PersistentDataType.PrimitivePersistentDataType)
                    .getElements()
                    .values()
                    .toArray(new PersistentDataType[0]);

    private static final Map<String, NamespacedKey> CACHED_NAMESPACED_KEYS = new HashMap<>();

    /**
     * Gets the PersistentDataType of a key. This will only return the underlying {@link org.bukkit.persistence.PersistentDataType.PrimitivePersistentDataType}, not the wrapper class.
     *
     * @param pdc PersistentDataContainer
     * @param key Key
     * @return PersistentDataType
     */
    public static PersistentDataType<?, ?> getPersistentDataType(@NotNull final PersistentDataContainer pdc, @NotNull final NamespacedKey key) {
        notNull(pdc, "pdc");
        notNull(key, "key");
        for (final PersistentDataType<?, ?> dataType : PRIMITIVE_DATA_TYPES) {
            if (pdc.has(key, dataType)) return dataType;
        }
        return null;
    }

    /**
     * Copies all data from source to target
     *
     * @param source Source
     * @param target Target
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void copyFromTo(@NotNull final PersistentDataContainer source, @NotNull final PersistentDataContainer target) {
        notNull(source, "source");
        notNull(target, "target");
        source.getKeys().forEach(key -> {
            PersistentDataType dataType = getPersistentDataType(source, key);
            if (dataType == null) return;
            target.set(key, dataType, Objects.requireNonNull(source.get(key, dataType)));
        });
    }

    @NotNull
    public static NamespacedKey getKey(@NotNull final String key) {
        notNull(key, "key");
        return CACHED_NAMESPACED_KEYS.computeIfAbsent(key,
                (__) -> new NamespacedKey(Cesspool.plugin(), key));
    }

}
