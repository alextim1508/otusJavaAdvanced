package ru.otus.timofeev.task9.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.otus.timofeev.task9.dto.MessageDto;
import ru.otus.timofeev.task9.dto.NewUserDto;
import ru.otus.timofeev.task9.dto.UserDto;
import ru.otus.timofeev.task9.exception.ConflictException;
import ru.otus.timofeev.task9.model.User;
import ru.otus.timofeev.task9.service.UserMapper;
import ru.otus.timofeev.task9.service.UserService;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import java.util.function.Consumer;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private final UserMapper mapper;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto create(@Valid @RequestBody NewUserDto userDto) {
        log.info("Creation request. UserDto : {}", userDto);
        User user = mapper.toEntity(userDto);

        User savedUser = service.create(user);

        return mapper.toDto(savedUser);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDto getUser(@PathVariable Long id) {
        log.info("Getting user with ID {} request", id);
        User user = service.getById(id);
        return mapper.toDto(user);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserDto> getUsers() {
        log.info("Getting all users request");
        List<User> users = service.getUsers();
        return mapper.toDto(users);
    }


    @GetMapping("test/exceptions")
    @ResponseStatus(HttpStatus.OK)
    public MessageDto testThrowExceptions() {
        throw new ConflictException("exception", "");
    }

    @SneakyThrows
    @GetMapping("/test/synchronize")
    @ResponseStatus(HttpStatus.OK)
    public MessageDto testSynchronize() {
        synchronized (MessageDto.class) {
            Thread.sleep(200);
        }
        return new MessageDto("OK");
    }

    @SneakyThrows
    @GetMapping("test/database")
    @ResponseStatus(HttpStatus.OK)
    public MessageDto testDatabaseRequests() {
        AtomicLong blackHole = new AtomicLong();

        for (int i = 0; i < 50; i++) {
            service.getUsers().forEach(user ->
                    blackHole.addAndGet(user.getId()));
        }

        return new MessageDto("OK: " + blackHole.get());
    }
}