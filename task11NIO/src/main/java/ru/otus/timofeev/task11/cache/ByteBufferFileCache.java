package ru.otus.timofeev.task11.cache;


import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;
import java.util.WeakHashMap;


@RequiredArgsConstructor
public class ByteBufferFileCache implements Cache<String, ByteBuffer> {

    private final WeakHashMap<String, ByteBuffer> cache;

    @Override
    public void put(String key, ByteBuffer value) {
        cache.put(key, value);
    }

    @Override
    public ByteBuffer get(String key) {
        return cache.get(key);
    }

    @Override
    public ByteBuffer remove(String key) {
        return cache.remove(key);
    }

    @Override
    public int size() {
        return cache.size();
    }

    @SneakyThrows
    @Override
    public ByteBuffer readFile(String fullNameFile) {
        try (SeekableByteChannel byteChannel = Files.newByteChannel(Path.of(fullNameFile),
                EnumSet.of(StandardOpenOption.READ))) {
            ByteBuffer buf = ByteBuffer.allocateDirect((int) byteChannel.size());
            byteChannel.read(buf);
            return buf;
        }
    }

    @Override
    public String getContent(ByteBuffer buffer)  {
        StringBuilder content = new StringBuilder();

        buffer.flip();

        CharBuffer cb = StandardCharsets.UTF_8.decode(buffer);
        while (cb.hasRemaining())
            content.append(cb.get());
        return content.toString();
    }
}