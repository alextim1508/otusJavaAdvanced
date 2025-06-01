package ru.otus.timofeev.task18.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.timofeev.task18.client.ClientAdapter;

@Component
@RequiredArgsConstructor
public class CustomerService {

    private final ClientAdapter clientAdapter;

    public int getAge() {
        return clientAdapter.circuitBreakerApi();
    }
}
