package ru.otus.timofeev.task18.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class ClientAdapter {

    private final RPMRateLimitedSearch rpmRateLimitedSearch;

    @RateLimiter(name = "default")
    @CircuitBreaker(name="default")
    public Integer circuitBreakerApi() {
        log.info("Call RateLimiter CircuitBreaker API");
        return rpmRateLimitedSearch.callRateLimiterApi();
    }
}
