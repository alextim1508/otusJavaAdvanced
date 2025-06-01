package ru.otus.timofeev.task18.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.IntStream;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import io.github.resilience4j.ratelimiter.RateLimiterRegistry;
import ru.otus.timofeev.task18.client.ClientApi;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Slf4j
class RPSRateLimiterTest {

    private static final String CUSTOMER_URL = "/customer/age";

    @Autowired
    private TestRestTemplate testRestTemplate;

    @MockBean
    private ClientApi clientApi;


    @Autowired
    private RateLimiterRegistry rateLimiterRegistry;

    @BeforeEach
    public void setUp() {
        rateLimiterRegistry.remove("rpm_limiter");
    }

    @Test
    void testRateLimiter_20rps_only20executed() {
        var limitForPeriod = 20;
        var countRequest = limitForPeriod + 5;

        Mockito.when(clientApi.callApi()).thenReturn(18);

        var responses = new CopyOnWriteArrayList<ResponseEntity<String>>();

        IntStream.rangeClosed(1, countRequest)
                .parallel()
                .forEach(it -> responses.add(testRestTemplate.getForEntity(CUSTOMER_URL, String.class)));

        assertEquals(countRequest, responses.size());

        assertEquals(limitForPeriod,
                responses.stream().filter(it -> it.getStatusCode() == HttpStatus.OK).count());

        assertEquals(countRequest - limitForPeriod,
                 responses.stream().filter(it -> it.getStatusCode() == HttpStatus.TOO_MANY_REQUESTS).count());

        verify(clientApi, times(limitForPeriod)).callApi();
    }
}