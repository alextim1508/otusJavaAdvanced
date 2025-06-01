package ru.otus.timofeev.task5.core.util;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
@Scope(value = "prototype")
public class IdGeneratorIdImpl implements IdGenerator<Long> {

    private final AtomicLong id;

    public IdGeneratorIdImpl() {
        id = new AtomicLong();
    }

    @Override
    public Long generateId() {
        return id.incrementAndGet();
    }
}
