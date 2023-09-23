package com.jeff_media.cesspool.tuples;

import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 * Tuple with one value
 * @param <A> Type of first element
 */
public class Unit<A> {

    protected void checkMutable() {
        if(!isMutable) throw new UnsupportedOperationException("This " + getClass().getSimpleName() + " is immutable");
    }

    protected boolean isMutable = false;

    @Nullable A first;

    /**
     * Creates a new mutable Unit with the given first element
     * @param first First element
     */
    public Unit(A first) {
        this(true, first);
    }

    /**
     * Creates a new Unit with the given first element and mutability
     * @param isMutable Whether this Unit is mutable
     * @param first First element
     */
    public Unit(boolean isMutable, @Nullable A first) {
        this.isMutable = isMutable;
        this.first = first;
    }

    @Nullable
    public final A getFirst() {
        return first;
    }

    /**
     * Returns whether this Tuple is mutable
     * @return Whether this Tuple is mutable
     */
    public final boolean isMutable() {
        return isMutable;
    }

    /**
     * Sets the first element of this Unit if it is mutable
     * @param first New first element
     * @throws UnsupportedOperationException if this Unit is immutable
     */
    public final void setFirst(@Nullable A first) throws UnsupportedOperationException {
        checkMutable();
        this.first = first;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "isMutable=" + isMutable +
                ", first=" + first +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Unit<?> unit = (Unit<?>) o;
        return isMutable == unit.isMutable && Objects.equals(first, unit.first);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isMutable, first);
    }
}
