package ru.job4j.bank;

import java.util.Objects;

/**
 * Класс описывает свойства и поведение пользователя банковских услуг
 * (клиента банка)
 *
 * @author Nikolai Gladkikh
 * @version 1.0
 */
public class User {

    /**
     * Паспорт пользователя
     */
    private String passport;
    /**
     * Имя пользователя
     */
    private String username;

    /**
     * Конструктор класса
     *
     * @param passport Номер паспорта пользователя
     * @param username Имя пользователя
     */
    public User(String passport, String username) {
        this.passport = passport;
        this.username = username;
    }

    /**
     * Метод возвращает номер паспорта пользователя
     *
     * @return Номер паспорта пользователя
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Метод устанавливает новое значение для
     * номера паспорта пользователя
     *
     * @param passport Новый номер паспорта пользователя
     */
    public void setPassport(String passport) {
        this.passport = passport;
    }

    /**
     * Метод возвращает имя пользователя
     * @return Имя пользователя
     */
    public String getUsername() {
        return username;
    }

    /**
     * Метод устанавливает новое значение для имени пользователя
     * @param username Новое имя пользователя
     */
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(passport, user.passport);
    }

    @Override
    public int hashCode() {
        return Objects.hash(passport);
    }
}
