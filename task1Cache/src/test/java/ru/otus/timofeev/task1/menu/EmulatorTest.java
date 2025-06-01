package ru.otus.timofeev.task1.menu;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.otus.timofeev.task1.cache.AbstractCache;
import ru.otus.timofeev.task1.service.FileService;
import ru.otus.timofeev.task1.service.ScannerService;

import static org.mockito.Mockito.*;

public class EmulatorTest {

    ScannerService scannerServiceMock;
    FileService fileServiceMock;

    Emulator emulator;

    @BeforeEach
    public void setUp() {
        scannerServiceMock = Mockito.mock(ScannerService.class);
        fileServiceMock = Mockito.mock(FileService.class);

        emulator = new Emulator(scannerServiceMock) {
            @Override
            FileService createFileService(AbstractCache cache) {
                return fileServiceMock;
            }
        };
    }

    @Test
    public void setFileDirMethodCallTest() {
        when(scannerServiceMock.readInt())
                .thenReturn(1) //create FileService with SoftRefCache
                .thenReturn(1) //select "Set directory path" option
                .thenReturn(7); //exit
        when(scannerServiceMock.readString()).thenReturn("D:/test");

        emulator.showMenu();

        verify(fileServiceMock, times(1)).setFileDir("D:/test");
    }

    @Test
    public void getFileDirMethodCallTest() {
        when(scannerServiceMock.readInt())
                .thenReturn(1) //create FileService with SoftRefCache
                .thenReturn(2) //select "Get directory path" option
                .thenReturn(7); //exit

        emulator.showMenu();

        verify(fileServiceMock, times(1)).getFileDir();
    }

    @Test
    public void getMethodCallTest() {
        when(scannerServiceMock.readInt())
                .thenReturn(1) //create FileService with SoftRefCache
                .thenReturn(3) //select "Get file" option
                .thenReturn(7); //exit
        when(scannerServiceMock.readString()).thenReturn("test.txt");

        emulator.showMenu();

        verify(fileServiceMock, times(1)).get("test.txt");
    }

    @Test
    public void putMethodCallTest() {
        when(scannerServiceMock.readInt())
                .thenReturn(1) //create FileService with SoftRefCache
                .thenReturn(4) //select "Load file" option
                .thenReturn(7); //exit
        when(scannerServiceMock.readString()).thenReturn(anyString());

        emulator.showMenu();

        verify(fileServiceMock, times(1)).put(Mockito.anyString());
    }

    @Test
    public void getCachedFilesMethodCallTest() {
        when(scannerServiceMock.readInt())
                .thenReturn(1) //create FileService with SoftRefCache
                .thenReturn(5) //select "Get cached files" option
                .thenReturn(7); //exit
        emulator.showMenu();

        verify(fileServiceMock, times(1)).getCachedFiles();
    }

    @Test
    public void cleanMethodCallTest() {
        when(scannerServiceMock.readInt())
                .thenReturn(1) //create FileService with SoftRefCache
                .thenReturn(6) //select "Clean cache" option
                .thenReturn(7); //exit
        emulator.showMenu();

        verify(fileServiceMock, times(1)).clean();
    }
}
