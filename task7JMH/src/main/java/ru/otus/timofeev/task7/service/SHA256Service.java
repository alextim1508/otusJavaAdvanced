package ru.otus.timofeev.task7.service;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@ConditionalOnProperty(prefix = "hashing", name = "algo", havingValue = "sha256")
public class SHA256Service implements HashPasswordService {

    @Override
    public long hashPass(String pass) {
        return Hashing.sha256().hashString(pass, StandardCharsets.UTF_8).asLong();
    }
}
