package ru.otus.timofeev.task5.hotelservice.service;

import ru.otus.timofeev.task5.hotelservice.entity.Person;

import java.util.List;

public interface PersonService {
    Person create(Person person);

    Person findById(long id);

    List<Person> findAll();
}
