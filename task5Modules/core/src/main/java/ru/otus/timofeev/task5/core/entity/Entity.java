package ru.otus.timofeev.task5.core.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class Entity<K> {
    private K id;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}