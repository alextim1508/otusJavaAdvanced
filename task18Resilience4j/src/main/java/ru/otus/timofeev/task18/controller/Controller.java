package ru.otus.timofeev.task18.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.timofeev.task18.service.CustomerService;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class Controller {

    private final CustomerService customerService;

    @GetMapping(value = "/age")
    public int getAge() {
        return customerService.getAge();
    }
}