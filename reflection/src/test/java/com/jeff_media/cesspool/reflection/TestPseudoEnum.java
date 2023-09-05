package com.jeff_media.cesspool.reflection;

import org.bukkit.Color;
import org.bukkit.persistence.PersistentDataType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestPseudoEnum {

    @Test
    public void bukkit_color() {
        PseudoEnum<Color> colors = PseudoEnum.of(Color.class);
        assertEquals(Color.AQUA, colors.valueOf("AQUA"));
    }

    @Test
    public void bukkit_persistentdatatypes() {
        PseudoEnum<PersistentDataType> types = PseudoEnum.of(PersistentDataType.class);
        assertEquals(PersistentDataType.BYTE, types.valueOf("BYTE"));
        assertEquals(PersistentDataType.BOOLEAN, types.valueOf("BOOLEAN"));
    }
}
