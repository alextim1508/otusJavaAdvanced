package ru.otus.timofeev.task11.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.timofeev.task11.cache.Cache;

import java.nio.Buffer;
import java.nio.file.Files;
import java.nio.file.Path;

@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final Cache<String, Buffer> cache;

    public String put(String fullNameFile) {
        if (!Files.exists(Path.of(fullNameFile)))
            throw new RuntimeException(fullNameFile + "is not exists");

        try {
            Buffer buffer = cache.readFile(fullNameFile);
            cache.put(fullNameFile, buffer);
            log.info("{} added to cache", fullNameFile);
            return fullNameFile + " added to cache";
        } catch (Exception e) {
            log.error("Couldn't read " + fullNameFile, e);
            return "Couldn't read " + fullNameFile;
        }
    }

    public String get(String fullNameFile) {
        if (!Files.exists(Path.of(fullNameFile)))
            throw new RuntimeException(fullNameFile + "is not exists");

        try {
            Buffer buffer = cache.get(fullNameFile);
            if (buffer == null) {
                throw new RuntimeException(fullNameFile + "is not cached");
            }

            String content = cache.getContent(buffer);
            log.info("Get {} file content from cache successfully", fullNameFile);
            return content;

        } catch (Exception e) {
            log.error("Couldn't read " + fullNameFile, e);
            return "Couldn't read " + fullNameFile;
        }
    }

}