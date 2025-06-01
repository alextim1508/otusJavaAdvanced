package ru.otus.timofeev.task1.service;

import ru.otus.timofeev.task1.cache.AbstractCache;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class FileService {

    private final AbstractCache cache;

    @Setter
    @Getter
    private String fileDir;

    public String put(String fileName) {
        if (fileDir == null)
            throw new IllegalStateException("FileDir is not set");

        String fullNameFile = fileDir + "/" + fileName;

        try {
            String content = readFile(fullNameFile);
            cache.put(fileName, content);
            log.info("{} added to cache", fullNameFile);
            return fullNameFile + " added to cache";

        } catch (Exception e) {
            log.error("Couldn't read " + fullNameFile, e);
            return "Couldn't read " + fullNameFile;
        }
    }

    public String get(String fileName) {
        if (fileDir == null)
            throw new IllegalStateException("FileDir is not set");

        String fullNameFile = fileDir + "/" + fileName;

        try {
            String content = cache.get(fileName);
            if (content != null) {
                log.info("Get {} file content from cache successfully", fullNameFile);
            } else {
                content = readFile(fullNameFile);
                log.info("Read {} file content", fullNameFile);
            }
            return content;

        } catch (Exception e) {
            log.error("Couldn't read " + fullNameFile, e);
            return "Couldn't read " + fullNameFile;
        }
    }

    public List<String> getCachedFiles() {
        return cache.getCachedFiles();
    }

    public String clean() {
        cache.clean();
        log.info("Cache is clean");
        return "Cache is clean";
    }

    String readFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        return new String(bytes, Charset.defaultCharset());
    }
}