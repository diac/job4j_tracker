package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает внутреннюю стркутуру и поведение банковского счета
 *
 * @author Nikolai Gladkikh
 * @version 1.0
 */
public class Account {

    /**
     * Реквизиты банковского счета
     */
    private String requisite;
    /**
     * Баланс средств на счете
     */
    private double balance;

    /**
     * Конструктор класса
     *
     * @param requisite Реквизиты банковского счета
     * @param balance   Баланс средств на счете
     */
    public Account(String requisite, double balance) {
        this.requisite = requisite;
        this.balance = balance;
    }

    /**
     * Метод возвращает реквизиты банковского счета
     *
     * @return Реквизиты банковского счета
     */
    public String getRequisite() {
        return requisite;
    }

    /**
     * Метод устанавливает реквизиты для банковского счета
     *
     * @param requisite Новые реквизиты банковского счета
     */
    public void setRequisite(String requisite) {
        this.requisite = requisite;
    }

    /**
     * Метод возвращает баланс средств на счете
     *
     * @return Баланс средств на счете
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Метод устанавливает баланс средств на счете
     *
     * @param balance Новый баланс средств на счете
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Account account = (Account) o;
        return Objects.equals(requisite, account.requisite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requisite);
    }
}
