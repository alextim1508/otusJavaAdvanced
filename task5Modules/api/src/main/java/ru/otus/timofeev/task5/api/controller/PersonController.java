package ru.otus.timofeev.task5.api.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.otus.timofeev.task5.api.dto.NewPersonDto;
import ru.otus.timofeev.task5.api.dto.PersonDto;
import ru.otus.timofeev.task5.api.service.PersonMapper;
import ru.otus.timofeev.task5.hotelservice.entity.Person;
import ru.otus.timofeev.task5.hotelservice.service.PersonService;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@Slf4j
@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService service;

    private final PersonMapper mapper;

    @PostMapping
    @ResponseStatus(CREATED)
    public PersonDto createPerson(@Valid @RequestBody NewPersonDto personDto) {
        log.info("Creation person request: {}", personDto);
        Person person = mapper.toEntity(personDto);

        log.info("person : {}", person);
        Person savedPerson = service.create(person);

        PersonDto dto = mapper.toDto(savedPerson);
        log.info("response: {}", dto);
        return dto;
    }

    @GetMapping
    @ResponseStatus(OK)
    public List<PersonDto> getAllPersons() {
        log.info("Getting all person request");

        List<Person> persons = service.findAll();
        log.info("persons: {}", persons);

        List<PersonDto> dtos = persons.stream().map(mapper::toDto).collect(Collectors.toList());
        log.info("response: {}", dtos);
        return dtos;
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public PersonDto getPerson(@PathVariable Long id) {
        log.info("Getting by {} id person request", id);

        Person byId = service.findById(id);
        log.info("person: {}", byId);

        PersonDto dto = mapper.toDto(byId);
        log.info("response: {}", dto);
        return dto;
    }
}
