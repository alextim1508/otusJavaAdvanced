package ru.otus.timofeev.task18.client;

import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class RPMRateLimitedSearch {

    private final ClientApi clientApi;

    @RateLimiter(name = "rpm_limiter")
    public Integer callRateLimiterApi() {
        log.info("Call rpm_limiter API");
        return clientApi.callApi();
    }
}
