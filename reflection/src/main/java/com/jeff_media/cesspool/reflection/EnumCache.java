package com.jeff_media.cesspool.reflection;


import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

/**
 * A simple {@link Enum} cache that caches the enum constants and size of the enum
 *
 * @param <E> The enum type
 */
public final class EnumCache<E extends Enum<E>> implements Iterable<E> {

    private static final Map<Class<? extends Enum<?>>, EnumCache<?>> CACHE = new HashMap<>();

    private final Class<E> enumClass;
    private final List<E> enumConstants;
    private final int size;

    private EnumCache(Class<E> enumClass) {
        this.enumClass = enumClass;
        this.enumConstants = Collections.unmodifiableList(Arrays.asList(enumClass.getEnumConstants()));
        this.size = enumConstants.size();
    }

    /**
     * Creates a new or returns the cached {@link EnumCache} for the given enum class
     *
     * @param enumClass The enum class
     * @param <E>       The enum type
     * @return The {@link EnumCache} for the given enum class
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static <E extends Enum<E>> EnumCache<E> of(Class<E> enumClass) {
        return (EnumCache<E>) CACHE.computeIfAbsent(enumClass, __ -> new EnumCache<>(enumClass));
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return enumConstants.iterator();
    }

    @Override
    public Spliterator<E> spliterator() {
        return enumConstants.spliterator();
    }

    /**
     * Returns the enum class
     * @return The enum class
     */
    @NotNull
    public Class<@NotNull E> getEnumClass() {
        return this.enumClass;
    }

    /**
     * Returns an unmodifiable list of the enum constants
     *
     * @return An unmodifiable list of the enum constants
     */
    @NotNull
    public List<@NotNull E> getEnumConstants() {
        return this.enumConstants;
    }

    /**
     * Returns the size of the enum
     *
     * @return The size of the enum
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Returns the enum constant with the given name, ignoring case
     * @param name The name of the enum constant
     * @return The enum constant with the given name, ignoring case, or null if not found
     */
    @Nullable
    public E getByName(@NotNull String name) {
        notNull(name, "name");
        return enumConstants.stream().filter(e -> e.name().equalsIgnoreCase(name)).findFirst().orElse(null);
    }
}
