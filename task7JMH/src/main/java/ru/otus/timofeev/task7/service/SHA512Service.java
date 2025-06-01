package ru.otus.timofeev.task7.service;

import com.google.common.hash.Hashing;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@ConditionalOnProperty(prefix = "hashing", name = "algo", havingValue = "sha512")
public class SHA512Service implements HashPasswordService {

    @Override
    public long hashPass(String pass) {
        return Hashing.sha512().hashString(pass, StandardCharsets.UTF_8).asLong();
    }
}
