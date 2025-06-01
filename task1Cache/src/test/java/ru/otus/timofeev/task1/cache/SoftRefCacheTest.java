package ru.otus.timofeev.task1.cache;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SoftRefCacheTest {

    AbstractCache cache;

    @BeforeEach
    public void setUp() {
        cache = new SoftRefCache();
    }

    @Test
    public void getTheSameValueAfterPutTest() {
        cache.put("key", "value");

        assertEquals("value", cache.get("key"));
    }

    @Test
    public void checkSizeTest() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        assertEquals(2, cache.size());
    }

    @Test
    public void getNoValuesAfterCleanTest() {
        cache.put("key1", "value1");
        cache.put("key2", "value2");

        cache.clean();

        assertEquals(0, cache.size());
    }
}
