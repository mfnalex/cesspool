package com.jeff_media.cesspool.reflection;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import static com.jeff_media.cesspool.CesspoolUtils.notNull;

/**
 * A simple constant that has a name and a value
 *
 * @param <T> The type of the constant
 */
public class Constant<T> {

    @NotNull
    private final String name;
    @NotNull
    private final T value;

    private Constant(@NotNull String name, @NotNull T value) {
        this.name = notNull(name, "name");
        this.value = notNull(value, "value");
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Constant<?> constant = (Constant<?>) o;
        return Objects.equals(name, constant.name) && Objects.equals(value, constant.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, value);
    }

    /**
     * Creates a new {@link Constant} with the given name and value
     *
     * @param name  The name
     * @param value The value
     * @return The {@link Constant}
     */
    public static <T> Constant<T> of(@NotNull String name, @NotNull T value) {
        return new Constant<>(name, value);
    }


    @NotNull
    public String name() {
        return name;
    }

    @NotNull
    public T value() {
        return value;
    }

    @Override
    public String toString() {
        return "Constant{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }
}
