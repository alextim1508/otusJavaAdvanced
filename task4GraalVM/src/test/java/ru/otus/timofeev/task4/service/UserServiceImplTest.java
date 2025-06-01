package ru.otus.timofeev.task4.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.timofeev.task4.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class UserServiceImplTest {
    @Autowired
    private UserService service;

    @Test
    void testCreateUser() {
        User user = new User("alex", "timofeev", 123L);

        service.create(user);

        List<User> users = service.getUsers();

        assertEquals(1, users.size());
        assertEquals("alex", users.get(0).getName());
    }
}