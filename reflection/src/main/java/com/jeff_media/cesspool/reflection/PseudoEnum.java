package com.jeff_media.cesspool.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

/**
 * A simple cache to access class constants by name for "pseudo-enums" like {@link org.bukkit.Color}
 * @param <T> The type of the constants
 */
public class PseudoEnum<T> {

    @NotNull
    private final Map<@NotNull String, @NotNull T> elements;

    private PseudoEnum(@NotNull Map<@NotNull String, @NotNull T> elements) {
        this.elements = elements;
    }

    /**
     * Creates a new {@link PseudoEnum} for the given class
     * @param clazz The class
     * @return The {@link PseudoEnum}
     * @param <T> The type of the constants
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <T> PseudoEnum<T> of(@NotNull final Class<T> clazz) {
        notNull(clazz, "clazz");
        final Map<String,T> elements = new HashMap<>();
        for (Field field : clazz.getDeclaredFields()) {
            if (clazz.isAssignableFrom(field.getType())) {
                if (Modifier.isStatic(field.getModifiers())
                        && Modifier.isPublic(field.getModifiers())) {
                    try {
                        final String name = field.getName();
                        T element = (T) field.get(null);
                        if(element != null) {
                            elements.put(name,element);
                        }
                    } catch (ReflectiveOperationException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }
        return new PseudoEnum<>(Collections.unmodifiableMap(elements));
    }

    /**
     * Returns the constant with the given name or null if it doesn't exist
     * @param name The name
     * @return The constant or null
     */
    @Nullable
    public T valueOf(@NotNull String name) {
        notNull(name, "name");
        return elements.get(name);
    }

    /**
     * Returns an unmodifiable map of all constants
     * @return The map
     */
    @NotNull
    public Map<@NotNull String, @NotNull T> getElements() {
        return elements;
    }

}
