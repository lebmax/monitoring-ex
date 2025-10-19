package ru.yandex.practicum.users;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import ru.yandex.practicum.api.Account;
import ru.yandex.practicum.api.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class UsersService {

    private final RestClient accountsClient;

    public UsersService(RestClient.Builder restClientBuilder) {
        this.accountsClient = restClientBuilder
            .baseUrl("http://accounts:8080")
            .build();
    }

    public User getUser(String id) {
        int accountsCount = ThreadLocalRandom.current().nextInt(1, 5);
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < accountsCount; i++) {
            Account account = accountsClient.get()
                .uri("/accounts/" + UUID.randomUUID())
                .retrieve()
                .body(Account.class);

            accounts.add(account);
        }

        User user = new User();
        user.setId(id);
        user.setBalance(accounts.stream().mapToLong(Account::getBalance).sum());
        user.setAccounts(List.copyOf(accounts));
        return user;
    }

}
