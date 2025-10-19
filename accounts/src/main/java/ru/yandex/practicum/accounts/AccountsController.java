package ru.yandex.practicum.accounts;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.yandex.practicum.api.Account;

import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/accounts")
public class AccountsController {

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccount(@PathVariable String id) {
        Account account = createAccount(id);
        return ResponseEntity.ok(account);
    }

    private Account createAccount(String id) {
        // Эмуляция внутренних ошибок сервера (HTTP 500)
//         if (ThreadLocalRandom.current().nextInt(10) > 8) {
//             throw new RuntimeException("internal error");
//         }

        // Эмуляция задержек.
//         int sleep = ThreadLocalRandom.current().nextInt(100, 1000);
//         try {
//             Thread.sleep(sleep);
//         } catch (InterruptedException e) {
//             throw new RuntimeException(e);
//         }

        Account account = new Account();
        account.setId(id);
        account.setBalance(ThreadLocalRandom.current().nextLong(0, 10_000_000));
        return account;
    }

}
