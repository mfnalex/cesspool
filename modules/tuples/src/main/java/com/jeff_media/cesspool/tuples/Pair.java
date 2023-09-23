package com.jeff_media.cesspool.tuples;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Tuple of two values
 * @param <A> Type of first element
 * @param <B> Type of second element
 */
public class Pair <A,B> extends Unit<A> {

    @Nullable private B second;

    /**
     * Creates a new Pair with the given first and second elements and mutability
     * @param isMutable Whether this Pair is mutable
     * @param first First element
     * @param second Second element
     */
    public Pair(boolean isMutable, @Nullable A first, @Nullable B second) {
        super(isMutable, first);
    }

    /**
     * Creates a new mutable Pair with the given first and second elements
     * @param first First element
     * @param second Second element
     */
    public Pair(@Nullable A first, @Nullable B second) {
        this(true, first, second);
    }

    @Nullable
    public final B getSecond() {
        return second;
    }

    /**
     * Sets the second element of this Pair if it is mutable
     * @param second New second element
     * @throws UnsupportedOperationException if this Pair is immutable
     */
    public final void setSecond(@Nullable B second) throws UnsupportedOperationException {
        checkMutable();
        this.second = second;
    }

    @Override
    public String toString() {
        return "Pair{" +
                "isMutable=" + isMutable +
                ", first=" + first +
                ", second=" + second +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), second);
    }
}
