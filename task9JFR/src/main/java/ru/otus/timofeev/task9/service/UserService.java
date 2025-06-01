package ru.otus.timofeev.task9.service;


import ru.otus.timofeev.task9.model.User;

import java.util.List;

public interface UserService {
    User create(User userDto);

    User getById(Long id);

    List<User> getUsers();
}
