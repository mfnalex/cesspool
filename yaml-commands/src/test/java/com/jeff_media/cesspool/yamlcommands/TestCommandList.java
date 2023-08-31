package com.jeff_media.cesspoll.yamlcommands;

import com.jeff_media.cesspool.yamlcommands.CommandList;
import com.jeff_media.cesspool.yamlcommands.CommandSenderType;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestCommandList {

    private YamlConfiguration yaml;

    @BeforeAll
    public void setup() throws IOException {
        try(InputStream inputStream = TestCommandList.class.getResourceAsStream("/test.yml");
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        ) {
            yaml = YamlConfiguration.loadConfiguration(inputStreamReader);
        }
    }

    @Test
    public void testAll() {
        CommandList reference = CommandList.of("say hi", CommandSenderType.CONSOLE);

        CommandList sayHi = CommandList.of(yaml, "say-hi");
        CommandList sayHiAsList = CommandList.of(yaml, "say-hi-as-list");
        CommandList sayHiAsMap = CommandList.of(yaml, "say-hi-as-map");

        assertEquals(reference, sayHi);
        assertEquals(reference, sayHiAsList);
        assertEquals(reference, sayHiAsMap);
    }
}
