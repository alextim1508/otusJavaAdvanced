package ru.otus.timofeev.task1.cache;

import lombok.RequiredArgsConstructor;

import java.lang.ref.Reference;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public abstract class AbstractCache {

    protected final Map<String, Reference<String>> cache;

    public abstract void put(String key, String value);

    public String get(String key) {
        return cache.get(key) != null ? cache.get(key).get() : null;
    }

    public List<String> getCachedFiles() {
        if (cache.isEmpty()) {
            return Collections.singletonList("Cache is empty");
        } else {
            return cache.entrySet().stream()
                    .map(entry -> {
                        return entry.getKey() + ": " + entry.getValue().get();
                    })
                    .collect(Collectors.toList());
        }
    }

    public int size() {
        return cache.size();
    }

    public void clean() {
        cache.clear();
    }
}
