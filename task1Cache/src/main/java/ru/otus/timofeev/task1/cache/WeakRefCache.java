package ru.otus.timofeev.task1.cache;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

public class WeakRefCache extends AbstractCache {

    public WeakRefCache() {
        super(new HashMap<>());
    }

    public WeakRefCache(Map<String, Reference<String>> cache) {
        super(cache);
    }

    @Override
    public void put(String key, String value) {
        cache.put(key, new WeakReference<>(value));
    }
}
