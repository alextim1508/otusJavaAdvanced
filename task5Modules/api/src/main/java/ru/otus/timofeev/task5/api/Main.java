package ru.otus.timofeev.task5.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "ru.otus.timofeev.task5")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
