package ru.otus.timofeev.task1;

import ru.otus.timofeev.task1.menu.Emulator;
import ru.otus.timofeev.task1.service.ScannerService;

public class Main {
    public static void main(String[] args) {
        Emulator emulator = new Emulator(new ScannerService());
        emulator.showMenu();
    }
}
