package ru.otus.timofeev.task11;

import ru.otus.timofeev.task11.menu.Emulator;
import ru.otus.timofeev.task11.service.ScannerService;

public class Main {
    public static void main(String[] args) {
        Emulator emulator = new Emulator(new ScannerService());
        emulator.showMenu();
    }
}