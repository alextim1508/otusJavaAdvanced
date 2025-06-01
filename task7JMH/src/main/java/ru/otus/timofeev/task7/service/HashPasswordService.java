package ru.otus.timofeev.task7.service;

import org.mapstruct.Named;

public interface HashPasswordService {

    @Named("hashPass")
    long hashPass(String pass);
}
