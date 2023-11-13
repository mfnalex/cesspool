package com.jeff_media.cesspool.utils;

import com.jeff_media.cesspool.Cesspool;
import com.jeff_media.cesspool.reflection.ConstantsCache;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.jeff_media.cesspool.Validate.*;

/**
 * Utility methods to work with PersistentDataContainers
 */
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
        paramNotNull(pdc, "pdc");
        paramNotNull(key, "key");
        for (final PersistentDataType<?, ?> dataType : PRIMITIVE_DATA_TYPES) {
            if (pdc.has(key, dataType)) return dataType;
        }
        return null;
    }

    /**
     * Copies all data from source to target. Keys already existing in the target will be overwritten with the source's value if they appear in both.
     *
     * @param source Source PDC
     * @param target Target PDC
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    public static void copyFromTo(@NotNull final PersistentDataContainer source, @NotNull final PersistentDataContainer target) {
        paramNotNull(source, "source");
        paramNotNull(target, "target");
        source.getKeys().forEach(key -> {
            PersistentDataType dataType = getPersistentDataType(source, key);
            if (dataType == null) return;
            target.set(key, dataType, Objects.requireNonNull(source.get(key, dataType)));
        });
    }

    /*
    has
     */

    /**
     * Checks if a PersistentDataContainer has a key
     * @param pdc PersistentDataContainer
     * @param key Key
     * @return true if the PDC has the key, false otherwise
     */
    public static boolean has(PersistentDataContainer pdc, NamespacedKey key) {
        for(PersistentDataType<?,?> type : PRIMITIVE_DATA_TYPES) {
            if(pdc.has(key,type)) return true;
        }
        return false;
    }

    /**
     * Checks if a PersistentDataContainer has a key
     * @param pdc PersistentDataContainer
     * @param key Key as string
     * @return true if the PDC has the key, false otherwise
     */
    public static boolean has(PersistentDataContainer pdc, String key) {
        return has(pdc,NamespacedKeyUtils.getKey(key));
    }

    /**
     * Checks if a PersistentDataHolder's PDC has a key
     * @param holder PersistentDataHolder
     * @param key Key
     * @return true if the holder's PDC has the key, false otherwise
     */
    public static boolean has(PersistentDataHolder holder, NamespacedKey key) {
        return has(holder.getPersistentDataContainer(),key);
    }

    /**
     * Checks if a PersistentDataHolder's PDC has a key
     * @param holder PersistentDataHolder
     * @param key Key as string
     * @return true if the holder's PDC has the key, false otherwise
     */
    public static boolean has(PersistentDataHolder holder, String key) {
        return has(holder.getPersistentDataContainer(),key);
    }

    /*
    remove
     */

    /**
     * Removes a key from a PersistentDataContainer
     * @param pdc PersistentDataContainer
     * @param key Key
     */
    public static void remove(PersistentDataContainer pdc, NamespacedKey key) {
        pdc.remove(key);
    }

    /**
     * Removes a key from a PersistentDataContainer
     * @param pdc PersistentDataContainer
     * @param key Key as string
     */
    public static void remove(PersistentDataContainer pdc, String key) {
        remove(pdc,NamespacedKeyUtils.getKey(key));
    }

    /**
     * Removes a key from a PersistentDataHolder's PDC
     * @param holder PersistentDataHolder
     * @param key Key
     */
    public static void remove(PersistentDataHolder holder, NamespacedKey key) {
        remove(holder.getPersistentDataContainer(),key);
    }

    /**
     * Removes a key from a PersistentDataHolder's PDC
     * @param holder PersistentDataHolder
     * @param key Key as string
     */
    public static void remove(PersistentDataHolder holder, String key) {
        remove(holder.getPersistentDataContainer(),key);
    }

    /*
    set
     */
    public static <T,Z> void  set(ItemStack item, NamespacedKey key, PersistentDataType<T,Z> type, Z value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(key,type,value);
        item.setItemMeta(meta);
    }

    public static <T,Z> void  set(ItemStack item, String key, PersistentDataType<T,Z> type, Z value) {
        set(item,NamespacedKeyUtils.getKey(key),type,value);
    }


}
