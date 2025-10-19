package ru.yandex.practicum.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class Scheduler {

    private final RestClient usersClient;
    private final ExecutorService executorService;

    public Scheduler(RestClient.Builder restClientBuilder) {
        this.usersClient = restClientBuilder.baseUrl("http://users:8080").build();
        this.executorService = Executors.newCachedThreadPool();
    }

    @Scheduled(fixedRateString = "PT1s")
    public void schedule() {
        int count = ThreadLocalRandom.current().nextInt(25, 50);
        for (int i = 0; i < count; i++) {
            executorService.submit(() -> {
                usersClient.get()
                    .uri("/users/" + UUID.randomUUID())
                    .retrieve()
                    .toBodilessEntity();
            });
        }
    }

}
