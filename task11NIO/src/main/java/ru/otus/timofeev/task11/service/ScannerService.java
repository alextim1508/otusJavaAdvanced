package ru.otus.timofeev.task11.service;

import java.util.Scanner;

public class ScannerService {

    public int readInt() {
        return new Scanner(System.in).nextInt();
    }

    public String readString() {
        return new Scanner(System.in).nextLine();
    }

    public void writeString(String s) {
        System.out.println(s);
    }
}
