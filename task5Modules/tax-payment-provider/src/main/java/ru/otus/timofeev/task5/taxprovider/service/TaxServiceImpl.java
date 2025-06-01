package ru.otus.timofeev.task5.taxprovider.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.timofeev.task5.taxprovider.entity.Tax;
import ru.otus.timofeev.task5.taxprovider.exception.TaxNotFoundException;
import ru.otus.timofeev.task5.taxprovider.repository.TaxRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxServiceImpl implements TaxService {

    private final TaxRepository repository;

    @Override
    public Tax create(Tax tax) {
        return repository.save(tax);
    }

    @Override
    public Tax findById(long id) {
        return repository.findById(id).orElseThrow(() -> new TaxNotFoundException(id));
    }

    @Override
    public List<Tax> findAll() {
        return repository.findAll();
    }
}
