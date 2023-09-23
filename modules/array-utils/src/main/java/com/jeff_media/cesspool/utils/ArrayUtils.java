package com.jeff_media.cesspool.utils;


import java.lang.reflect.Array;
import java.util.Arrays;
import org.jetbrains.annotations.NotNull;

/**
 * Array related methods
 */
public final class ArrayUtils {

    private ArrayUtils() { }

    /**
     * Combines the given arrays into a new array
     *
     * @param array Arrays to combine
     * @param <T>   Array's component type
     * @return Combined array
     */
    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <T> T[] combine(final T[]... array) {
        return (T[]) Arrays.stream(array).flatMap(Arrays::stream).toArray();
    }

    /**
     * Returns a new array of the given class type with length 0
     *
     * @param componentType Class of the array's component type
     * @param <T>           Array's component type
     * @return Array of the given class type with length 0
     */
    public static <T> T[] createArray(@NotNull final Class<T> componentType) {
        return createArray(componentType, 0);
    }

    /**
     * Returns a new array of the given class type with the given length
     *
     * @param componentType Class of the array's component type
     * @param <T>           Array's component type
     * @param length        Array's length
     * @return Array of the given class type with the given length
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] createArray(@NotNull final Class<T> componentType, final int length) {
        return (T[]) Array.newInstance(componentType, length);
    }

    /**
     * Removes an item from the array at a given location, returning the remaining array
     *
     * @param arr   Array to remove from
     * @param index Index at which to remove from
     * @param <T>   Array type
     * @return Array with the desired item removed
     */
    @SuppressWarnings("unchecked")
    public static <T> T[] removeAtIndex(final T[] arr, final int index) {
        T[] newArr = createArray((Class<T>) arr.getClass().getComponentType(), arr.length - 1);
        System.arraycopy(arr, 0, newArr, 0, index);
        System.arraycopy(arr, index + 1, newArr, index, arr.length - index - 1);
        return newArr;
    }

    /**
     * Appends an item to the given array
     *
     * @param arr    Array to append to
     * @param object Object to append
     * @param <T>    Array type
     * @return Array with the desired item appended
     */
    public static <T> T[] addAfter(final T[] arr, final T object) {
        T[] newArr = Arrays.copyOf(arr, arr.length + 1);
        newArr[arr.length] = object;
        return newArr;
    }
}
