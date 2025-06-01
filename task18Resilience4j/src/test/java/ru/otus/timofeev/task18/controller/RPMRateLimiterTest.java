package ru.otus.timofeev.task18.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.otus.timofeev.task18.client.ClientApi;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class RPMRateLimiterTest {
    private static final String CUSTOMER_URL = "/customer/age";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientApi clientApi;

    @Test
    void testNotRpmLimit_30rpm_only30executed() {
        var countRequestRps = 15;
        var countRequestRpm = 30;
        var repeat = 2;

        Mockito.when(clientApi.callApi()).thenReturn(18);

        var responses = new CopyOnWriteArrayList<ResponseEntity<String>>();

        IntStream.rangeClosed(1, repeat)
                .forEach(it -> {
                            IntStream.rangeClosed(1, countRequestRps)
                                    .forEach(it1 ->
                                            responses.add(testRestTemplate.getForEntity(CUSTOMER_URL, String.class)));
                            sleep();
                        }
                );

        assertEquals(countRequestRps * repeat, responses.size());

        assertEquals(countRequestRpm,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());

        assertEquals(countRequestRps * repeat - countRequestRpm,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());

        verify(clientApi, times(countRequestRpm)).callApi();
    }

    private void sleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}