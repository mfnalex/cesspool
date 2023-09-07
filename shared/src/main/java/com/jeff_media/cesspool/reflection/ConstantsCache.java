package com.jeff_media.cesspool.reflection;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Spliterator;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

/**
 * A simple cache to access public static final fields of a class as if they were declared as an enum
 *
 * @param <T> The type of the constants
 */
public class ConstantsCache<T> implements Iterable<Constant<T>> {

    @NotNull
    private final Class<?> declaringClazz;

    @NotNull
    private final Class<T> constantType;

    @NotNull
    private final Map<@NotNull String, @NotNull T> elements;

    @SuppressWarnings("unchecked")
    public ConstantsCache(@NotNull Class<?> declaringClazz, Class<T> constantType, @Nullable Predicate<@NotNull T> predicate) {
        this.declaringClazz = notNull(declaringClazz, "declaringClazz");
        this.constantType = notNull(constantType, "constantType");
        final Map<String, T> elements = new HashMap<>();
        for (Field field : this.declaringClazz.getDeclaredFields()) {
            if (this.constantType.isAssignableFrom(field.getType())) {
                if (Modifier.isStatic(field.getModifiers())
                        && Modifier.isPublic(field.getModifiers())) {
                    try {
                        final String name = field.getName();
                        T element = (T) field.get(null);
                        if (element != null) {
                            if(predicate == null || predicate.test(element)) {
                                elements.put(name, element);
                            }
                        }
                    } catch (ReflectiveOperationException exception) {
                        throw new RuntimeException(exception);
                    }
                }
            }
        }
        this.elements = elements;
    }

    ConstantsCache(@NotNull Class<T> declaringClazz) {
        this(declaringClazz, declaringClazz, null);
    }

    /**
     * Creates a new {@link ConstantsCache} for the given class, containing all public static final fields that are of the same type as the class
     *
     * @param clazz The class
     * @param <T>   The type of the constants
     * @return The {@link ConstantsCache}
     */
    @NotNull
    public static <T> ConstantsCache<T> of(@NotNull final Class<T> clazz) {
        return new ConstantsCache<>(clazz);
    }

    /**
     * Creates a new {@link ConstantsCache} for the given class, containing all public static final fields that are of the same type as the given type
     *
     * @param declaringClazz The class that declares the constants
     * @param constantType   The type of the constants
     * @param <T>            The type of the constants
     * @return The {@link ConstantsCache}
     */
    @NotNull
    public static <T> ConstantsCache<T> of(@NotNull final Class<?> declaringClazz, @NotNull final Class<T> constantType) {
        return new ConstantsCache<>(declaringClazz, constantType, null);
    }

    /**
     * Returns the constant with the given name or null if it doesn't exist
     *
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
     *
     * @return The map
     */
    @NotNull
    public Map<@NotNull String, @NotNull T> getElements() {
        return elements;
    }

    @NotNull
    @Override
    public Iterator<Constant<T>> iterator() {
        return stream().iterator();
    }

    @Override
    public Spliterator<Constant<T>> spliterator() {
        return stream().spliterator();
    }

    /**
     * Returns a stream of all constants
     *
     * @return The stream
     */
    public Stream<Constant<T>> stream() {
        return elements.entrySet().stream().map(entry -> Constant.<T>of(entry.getKey(), entry.getValue()));
    }

    /**
     * Returns the number of constants
     *
     * @return The number of constants
     */
    public int getSize() {
        return elements.size();
    }

    /**
     * Returns the class of the constants
     *
     * @return The class of the constants
     */
    @NotNull
    public Class<T> getConstantsClass() {
        return constantType;
    }

    /**
     * Returns the class that declares the constants
     *
     * @return The class that declares the constants
     */
    @NotNull
    public Class<?> getDeclaringClass() {
        return declaringClazz;
    }
}
