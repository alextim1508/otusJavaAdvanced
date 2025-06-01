package ru.otus.timofeev.task1.menu;

import ru.otus.timofeev.task1.cache.AbstractCache;
import ru.otus.timofeev.task1.cache.SoftRefCache;
import ru.otus.timofeev.task1.cache.WeakRefCache;
import ru.otus.timofeev.task1.service.FileService;
import ru.otus.timofeev.task1.service.ScannerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.InputMismatchException;

@Slf4j
@RequiredArgsConstructor
public class Emulator {

    private static final String ACTIONS_STRING = """
            =====================
            Select action:
                1) Set directory
                2) Get directory
                3) Get file
                4) Load file
                5) Get cached files
                6) Clean cache
                7) Quit
            =====================
            """;

    private FileService fileService;

    private final ScannerService scannerService;

    void selectCacheType() {
        while (true) {
            scannerService.writeString("Select type of cache: 1- softRef, 2 - weakRef");

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
                    fileService = createFileService(new SoftRefCache());
                    log.info("Cache type: Soft");
                    return;
                }

                case 2 -> {
                    fileService = createFileService(new WeakRefCache());
                    log.info("Cache type: Weak");
                    return;
                }

                default -> {
                    log.info("Incorrect input: {}", input);
                    scannerService.writeString("Incorrect input, try again");
                }
            }
        }
    }

    FileService createFileService(AbstractCache cache) {
        return new FileService(cache);
    }

    public void showMenu() {
        selectCacheType();

        while (true) {
            scannerService.writeString(ACTIONS_STRING);

            try {
                switch (scannerService.readInt()) {
                    case 1 -> {
                        scannerService.writeString("Set path: ");
                        fileService.setFileDir(scannerService.readString());
                    }

                    case 2 -> {
                        scannerService.writeString(
                                fileService.getFileDir());
                    }

                    case 3 -> {
                        scannerService.writeString("Write file name:");
                        scannerService.writeString(
                                fileService.get(scannerService.readString()));
                    }

                    case 4 -> {
                        scannerService.writeString("Write file name:");
                        scannerService.writeString(
                                fileService.put(scannerService.readString()));
                    }

                    case 5 -> {
                        fileService.getCachedFiles().forEach(scannerService::writeString);
                    }

                    case 6 -> {
                        scannerService.writeString(
                                fileService.clean());
                    }

                    case 7 -> {
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