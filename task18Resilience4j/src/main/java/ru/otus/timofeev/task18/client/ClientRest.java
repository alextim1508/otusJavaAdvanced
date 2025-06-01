package ru.otus.timofeev.task18.client;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ClientRest implements ClientApi {

    @Override
    public int callApi() {
        log.info("callApi");
        return 35;
    }
}
