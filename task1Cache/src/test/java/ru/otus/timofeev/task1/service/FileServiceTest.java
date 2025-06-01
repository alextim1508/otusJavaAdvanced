package ru.otus.timofeev.task1.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.timofeev.task1.cache.AbstractCache;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class FileServiceTest {

    AbstractCache mockCache;

    FileService fileService;

    @BeforeEach
    public void setUp() {
        mockCache = Mockito.mock(AbstractCache.class);
        fileService = new FileService(mockCache) {
            @Override
            String readFile(String path) throws IOException {
                if (path.equals("D:/test/exception.txt"))
                    throw new IOException("exception");

                return "fileContent";
            }
        };
    }

    @Test
    public void getExceptionWhenFileDirIsNotSetAfterPutFileTest() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                fileService.put("test.txt"));

        assertEquals("FileDir is not set", exception.getMessage());
    }

    @Test
    public void checkResultAfterAddingFileToCaseText() {
        doNothing().when(mockCache).put("test.txt", "fileContent");
        fileService.setFileDir("D:/test");

        String result = fileService.put("test.txt");

        verify(mockCache, times(1)).put("test.txt", "fileContent");

        assertEquals("D:/test/test.txt added to cache",  result);
    }

    @Test
    public void getExceptionAfterReadingNotExistingFileTest() {
        doNothing().when(mockCache).put(anyString(), anyString());
        fileService.setFileDir("D:/test");

        String result = fileService.put("exception.txt");

        assertEquals("Couldn't read D:/test/exception.txt" , result);
    }

    @Test
    public void getExceptionWhenFileDirIsNotSetAfterGetFileTest() {
        IllegalStateException exception = assertThrows(IllegalStateException.class, () ->
                fileService.get("test.txt"));

        assertEquals("FileDir is not set", exception.getMessage());
    }

    @Test
    public void checkResultAfterGettingFileToCaseText() {
        when(mockCache.get("test.txt")).thenReturn("fileContent");
        fileService.setFileDir("D:/test");

        String result = fileService.get("test.txt");

        verify(mockCache, times(1)).get("test.txt");

        assertEquals("fileContent",  result);
    }
}
