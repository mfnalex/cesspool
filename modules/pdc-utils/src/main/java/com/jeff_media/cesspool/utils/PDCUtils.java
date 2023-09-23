package com.jeff_media.cesspool.utils;

import com.jeff_media.cesspool.Cesspool;
import com.jeff_media.cesspool.reflection.ConstantsCache;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
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

    /*
    has
     */
    public static boolean has(PersistentDataContainer pdc, NamespacedKey key) {
        for(PersistentDataType<?,?> type : PRIMITIVE_DATA_TYPES) {
            if(pdc.has(key,type)) return true;
        }
        return false;
    }

    public static boolean has(PersistentDataContainer pdc, String key) {
        return has(pdc,NamespacedKeyUtils.getKey(key));
    }

    public static boolean has(PersistentDataHolder holder, NamespacedKey key) {
        return has(holder.getPersistentDataContainer(),key);
    }

    public static boolean has(PersistentDataHolder holder, String key) {
        return has(holder.getPersistentDataContainer(),key);
    }

    /*
    remove
     */
    public static void remove(PersistentDataContainer pdc, NamespacedKey key) {
        pdc.remove(key);
    }

    public static void remove(PersistentDataContainer pdc, String key) {
        remove(pdc,NamespacedKeyUtils.getKey(key));
    }

    public static void remove(PersistentDataHolder holder, NamespacedKey key) {
        remove(holder.getPersistentDataContainer(),key);
    }

    public static void remove(PersistentDataHolder holder, String key) {
        remove(holder.getPersistentDataContainer(),key);
    }




}
