package ru.otus.timofeev.task5.core.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.timofeev.task5.core.entity.Entity;
import ru.otus.timofeev.task5.core.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.*;

@Component
@RequiredArgsConstructor
public abstract class InMemoryRepository<K, E extends Entity<K>> {

    private final Map<K, E> storage = new HashMap<>();

    private final IdGenerator<K> idGenerator;

    public E save(E entity) {
        if (entity.getId() == null) {
            entity.setId(idGenerator.generateId());
            entity.setCreatedAt(LocalDateTime.now());
        }

        entity.setUpdatedAt(LocalDateTime.now());

        storage.put(entity.getId(), entity);

        return entity;
    }

    public Optional<E> findById(K id) {
        return Optional.ofNullable(storage.get(id));
    }

    public List<E> findAll() {
        return new ArrayList<>(storage.values());
    }

    public void delete(K id) {
        storage.remove(id);
    }
}