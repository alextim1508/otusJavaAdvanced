package ru.otus.timofeev.task5.taxprovider.service;

import ru.otus.timofeev.task5.taxprovider.entity.Tax;

import java.util.List;

public interface TaxService {
    Tax create(Tax tax);

    Tax findById(long id);

    List< Tax> findAll();
}
