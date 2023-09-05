package com.jeff_media.cesspool.reflection;

import org.bukkit.Color;
import org.bukkit.entity.TextDisplay;
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TestConstantsCache {

    @Test
    public void bukkit_color() {
        ConstantsCache<Color> cache = ConstantsCache.of(Color.class);
        assertEquals(Color.AQUA, cache.valueOf("AQUA"));
    }

    @Test
    public void bukkit_persistentdatatypes() {
        ConstantsCache<PersistentDataType> cache = ConstantsCache.of(PersistentDataType.class);
        assertEquals(PersistentDataType.BYTE, cache.valueOf("BYTE"));
        assertEquals(PersistentDataType.BOOLEAN, cache.valueOf("BOOLEAN"));
    }

    @Test
    public void weird_enum() {
        ConstantsCache<TestEnum> cache = ConstantsCache.of(TestEnum.class);
        assertEquals(TestEnum.TEST1, cache.valueOf("TEST1"));
        assertEquals(TestEnum.TEST2, cache.valueOf("TEST2"));
        assertEquals(TestEnum.TEST3, cache.valueOf("TEST3"));
        assertEquals(TestEnum.TEST1, cache.valueOf("TEST3"));
        assertEquals(TestEnum.TEST3, cache.valueOf("TEST1"));
    }


    enum TestEnum {
        TEST1,
        TEST2;

        public static final TestEnum TEST3 = TEST1;
    }


}
