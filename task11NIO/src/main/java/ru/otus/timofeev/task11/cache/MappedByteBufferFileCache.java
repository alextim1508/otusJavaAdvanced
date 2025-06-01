package ru.otus.timofeev.task11.cache;


import lombok.RequiredArgsConstructor;

import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.WeakHashMap;

@RequiredArgsConstructor
public class MappedByteBufferFileCache implements Cache<String, MappedByteBuffer> {

    private final WeakHashMap<String, MappedByteBuffer> cache;

    @Override
    public void put(String key, MappedByteBuffer value) {
        cache.put(key, value);
    }

    @Override
    public MappedByteBuffer get(String key) {
        return cache.get(key);
    }

    @Override
    public MappedByteBuffer remove(String key) {
        return cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @Override
    public MappedByteBuffer readFile(String fullNameFile) throws Exception {
        try (FileChannel byteChannel = (FileChannel) Files.newByteChannel(Path.of(fullNameFile),
                EnumSet.of(StandardOpenOption.READ))) {
            return byteChannel.map(FileChannel.MapMode.READ_ONLY, 0, byteChannel.size());
        }
    }

    @Override
    public String getContent(MappedByteBuffer buffer) {
        StringBuilder content = new StringBuilder();

        CharBuffer cb = StandardCharsets.UTF_8.decode(buffer);
        while (cb.hasRemaining())
            content.append(cb.get());
        return content.toString();
    }
}