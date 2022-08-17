package ru.job4j.collection;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class NotifyAccountTest {

    @Test
    public void whenTwoAccountsAddSent() {
        NotifyAccount notifyAccount = new NotifyAccount();
        List<Account> accounts = Arrays.asList(
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("142", "Petr Arsentev", "000001")
        );
        HashSet<Account> expect = new HashSet<>(
                Arrays.asList(
                        new Account("123", "Petr Arsentev", "eDer3432f"),
                        new Account("142", "Petr Arsentev", "000001")
                )
        );
        assertThat(notifyAccount.sent(accounts)).containsAll(expect);
    }

    @Test
    public void whenDuplicates() {
        NotifyAccount notifyAccount = new NotifyAccount();
        List<Account> accounts = Arrays.asList(
                new Account("12345", "John Doe", "100000"),
                new Account("123", "Petr Arsentev", "eDer3432f"),
                new Account("142", "Petr Arsentev", "000001"),
                new Account("12345", "John Doe", "33333")
        );
        HashSet<Account> expect = new HashSet<>(
                Arrays.asList(
                        new Account("123", "Petr Arsentev", "eDer3432f"),
                        new Account("142", "Petr Arsentev", "000001"),
                        new Account("12345", "John Doe", "100000")
                )
        );
        assertThat(notifyAccount.sent(accounts)).containsOnlyOnce(
                new Account("12345", "John Doe", "999999")
        );
    }
}