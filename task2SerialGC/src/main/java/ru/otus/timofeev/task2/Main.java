package ru.otus.timofeev.task2;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws InterruptedException {
        Map<String, String> stringContainer = new HashMap<>();
        System.out.println("Start of program!");
        String stringWithPrefix = "stringWithPrefix";
        final long startTime = System.currentTimeMillis();

        while (System.currentTimeMillis() - startTime <= 30_000) {
            for (int i = 0; i < 200_000; i++) {
                String newString = stringWithPrefix + i;
                stringContainer.put(newString, newString);
            }

            for (int i = 0; i < 200_000; i++) {
                String newString = stringWithPrefix + i;
                stringContainer.remove(newString);
            }

            Thread.sleep(100);
        }
    }
}
