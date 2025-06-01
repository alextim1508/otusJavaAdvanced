package ru.otus.timofeev.task11.menu;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ru.otus.timofeev.task11.cache.ByteBufferFileCache;
import ru.otus.timofeev.task11.cache.Cache;
import ru.otus.timofeev.task11.cache.MappedByteBufferFileCache;
import ru.otus.timofeev.task11.service.FileService;
import ru.otus.timofeev.task11.service.ScannerService;

import java.util.InputMismatchException;
import java.util.WeakHashMap;

@Slf4j
@RequiredArgsConstructor
public class Emulator {

    private static final String ACTIONS_STRING = """
            =====================
            Select action:
                1) Get file
                2) Load file
                3) Quit
            =====================
            """;

    private final ScannerService scannerService;

    private FileService fileService;

    void selectCacheType() {
        while (true) {
            scannerService.writeString(
                    "Select type of cache: 1 - ByteBufferFileCache, 2 - MappedByteBufferFileCache");

            int input;
            try {
                input = scannerService.readInt();
            } catch (Exception e) {
                log.error("", e);
                scannerService.writeString("Incorrect input, try again");
                continue;
            }

            switch (input) {
                case 1 -> {
                    fileService = createFileService(new ByteBufferFileCache(new WeakHashMap<>()));
                    log.info("Cache type: ByteBufferFileCache");
                    return;
                }

                case 2 -> {
                    fileService = createFileService(new MappedByteBufferFileCache(new WeakHashMap<>()));
                    log.info("Cache type: MappedByteBufferFileCache");
                    return;
                }

                default -> {
                    log.info("Incorrect input: {}", input);
                    scannerService.writeString("Incorrect input, try again");
                }
            }
        }
    }

    FileService createFileService(Cache cache) {
        return new FileService(cache);
    }

    public void showMenu() {
        selectCacheType();

        while (true) {
            scannerService.writeString(ACTIONS_STRING);

            try {
                switch (scannerService.readInt()) {
                    case 1 -> {
                        scannerService.writeString("Write file name:");
                        String content = fileService.get(scannerService.readString());
                        scannerService.writeString("Content file:");
                        scannerService.writeString(content);
                    }

                    case 2 -> {
                        scannerService.writeString("Write file name:");
                        String answer = fileService.put(scannerService.readString());
                        scannerService.writeString(answer);
                    }

                    case 3 -> {
                        log.info("Bye bye");
                        return;
                    }

                    default -> {
                        scannerService.writeString("Wrong action");
                    }
                }

            } catch (InputMismatchException e) {
                log.error("", e);
                scannerService.writeString("Wrong action");
            } catch (Exception e) {
                log.error("", e);
                scannerService.writeString("Something went wrong: " + e.getMessage());
            }
        }
    }
}