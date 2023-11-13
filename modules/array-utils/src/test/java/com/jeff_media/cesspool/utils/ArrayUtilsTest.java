package com.jeff_media.cesspool.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayUtilsTest {

    @Test
    void combine_Single_Arrays() {
        String[] a = new String[] {"a"};
        String[] b = new String[] {"b"};
        String[] ab = new String[] {"a", "b"};
        assertArrayEquals(ab, ArrayUtils.combine(a,b));
    }

    @Test
    void combine_Multiple_Bigger_Arrays() {
        String[] abc = new String[] {"a", "b", "c"};
        String[] def = new String[] {"d", "e", "f"};
        String[] hello_world = new String[] {"hello", "world"};
        String[] expected = new String[] {"a", "b", "c", "d", "e", "f", "hello", "world"};
        assertArrayEquals(expected, ArrayUtils.combine(abc,def,hello_world));
    }

    @Test
    void createArray() {
        String[] empty = ArrayUtils.createArray(String.class);
        String[] length_2 = ArrayUtils.createArray(String.class, 2);
        assertArrayEquals(new String[0], empty);
        assertArrayEquals(new String[2], length_2);
    }

    @Test
    void removeAtIndex() {
        Object[] arr = new Object[] {0,1,2,3,4,5};
        Object[] arr_3 = new Object[] {0,1,2,4,5};
        Object[] arr_0 = new Object[] {1,2,3,4,5};
        Object[] arr_2_4 = new Object[] {0,1,3,5};
        assertArrayEquals(arr_3, ArrayUtils.removeAtIndex(arr, 3));
    }

    @Test
    void addAfter() {
    }
}