package ru.otus.timofeev.task5.hotelservice.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.timofeev.task5.hotelservice.entity.Person;
import ru.otus.timofeev.task5.hotelservice.exception.PersonNotFoundException;
import ru.otus.timofeev.task5.hotelservice.repository.PersonRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository repository;

    @Override
    public Person create(Person person) {
        return repository.save(person);
    }

    @Override
    public Person findById(long id) {
        return repository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public List<Person> findAll() {
        return repository.findAll();
    }
}
