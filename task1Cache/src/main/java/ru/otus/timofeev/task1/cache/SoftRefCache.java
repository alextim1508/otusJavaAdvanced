package ru.otus.timofeev.task1.cache;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;

public class SoftRefCache extends AbstractCache {

    public SoftRefCache() {
        super(new HashMap<>());
    }

    public SoftRefCache(Map<String, Reference<String>> cache) {
        super(cache);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, new SoftReference<>(value));
    }
}
