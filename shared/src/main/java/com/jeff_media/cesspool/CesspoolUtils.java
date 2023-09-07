package com.jeff_media.cesspool;

import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.Entity;
import org.bukkit.profile.PlayerProfile;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

/**
 * Utility methods for Cesspool
 */
public class CesspoolUtils {
    /**
     * Throws a NullPointerException if the object is null
     * @param object Object
     * @param name Name of the object
     * @return Object
     * @param <T> Type of the object
     */
    @NotNull
    @Contract(value = "null, _ -> fail; _, _ -> param1", pure = true)
    public static  <T> T notNull(@Nullable T object, String name) {
        if(object != null) {
            return object;
        } else {
            throw new NullPointerException(name + " cannot be null");
        }
    }

    /**
     * Returns the UUID of the specified object, or null if it doesn't have one
     * @param any Object
     * @return UUID
     */
    @Nullable
    @Contract(pure = true)
    public static UUID uuid(@NotNull Object any) {
        if(any instanceof Entity) {
            return ((Entity) any).getUniqueId();
        }
        if(any instanceof OfflinePlayer) {
            return ((OfflinePlayer) any).getUniqueId();
        }
        if(any instanceof World) {
            return ((World) any).getUID();
        }

        if(any instanceof AnimalTamer) {
            return ((AnimalTamer) any).getUniqueId();
        }

        if(any instanceof AttributeModifier) {
            return ((AttributeModifier) any).getUniqueId();
        }

        if(McVersion.current().isAtLeast(McVersion.V1_18_1)) {
            if (any instanceof PlayerProfile) {
                return ((PlayerProfile) any).getUniqueId();
            }
        }

        return null;
    }
}
