package ru.otus.timofeev.task5.hotelservice.repository;

import org.springframework.stereotype.Repository;
import ru.otus.timofeev.task5.core.repository.InMemoryRepository;
import ru.otus.timofeev.task5.core.util.IdGenerator;
import ru.otus.timofeev.task5.hotelservice.entity.Person;


@Repository
public class PersonRepository extends InMemoryRepository<Long, Person> {
    public PersonRepository(IdGenerator<Long> idGenerator) {
        super(idGenerator);
    }
}
