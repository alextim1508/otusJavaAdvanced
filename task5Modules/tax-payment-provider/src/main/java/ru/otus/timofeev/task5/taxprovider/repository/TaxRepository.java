package ru.otus.timofeev.task5.taxprovider.repository;

import org.springframework.stereotype.Repository;
import ru.otus.timofeev.task5.core.repository.InMemoryRepository;
import ru.otus.timofeev.task5.core.util.IdGenerator;
import ru.otus.timofeev.task5.taxprovider.entity.Tax;

@Repository
public class TaxRepository extends InMemoryRepository<Long, Tax> {
    public TaxRepository(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
