package ru.job4j.bank;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Класс описывает внутреннюю структуру и поведение банковского сервиса
 *
 * @author Nikolai Gladkikh
 * @version 1.0
 */
public class BankService {

    /**
     * Перечень зарегистрированных пользователей и списков банковских счетов,
     * связанных с каждым пользователем
     */
    private final Map<User, List<Account>> users = new HashMap<>();

    /**
     * Метод добавляет нового пользователя в перечень пользователей
     * при условии, что такого пользователя еще нет в перечне
     *
     * @param user Объект-пользователь
     */
    public void addUser(User user) {
        users.putIfAbsent(user, new ArrayList<>());
    }

    /**
     * Метод добавляет новый банковский счет и связывает его с пользователем
     * при условии, что данный счет еще не был добавлен к данному пользователю.
     * Поиск пользователя осуществляется по номеру паспорта
     *
     * @param passport Номер паспорта
     * @param account  Объект-банковский счет
     */
    public void addAccount(String passport, Account account) {
        User user = findByPassport(passport);
        if (user != null) {
            List<Account> userAccounts = getAccounts(user);
            if (!userAccounts.contains(account)) {
                userAccounts.add(account);
            }
        }
    }

    /**
     * Метод возвращает найденного по номеру паспорта
     * пользователя из перечня. Если пользователь не найден,
     * возвращается null
     *
     * @param passport Номер паспорта
     * @return Объект-пользователь или null
     */
    public User findByPassport(String passport) {
        return users.keySet()
                .stream()
                .filter(user -> passport.equals(user.getPassport()))
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод возвращает объект-банковский счет, найденный по
     * номеру паспорта пользователя и реквизитам счета. Если пользователь
     * с указанным паспортом не существует, или если счет с указанными
     * реквизитами не существует, то возвращается null
     *
     * @param passport  Номер паспорта
     * @param requisite Реквизиты счета
     * @return Объект-банковский счет или null
     */
    public Account findByRequisite(String passport, String requisite) {
        return users.keySet()
                .stream()
                .filter(user -> user.equals(findByPassport(passport)))
                .flatMap(
                        user -> users.get(user)
                                .stream()
                                .filter(account -> requisite.equals(account.getRequisite()))
                )
                .findFirst()
                .orElse(null);
    }

    /**
     * Метод осуществляет перевод средств между счетами
     * путем списания средств со счета отправителя
     * и перевода средств на счет получателя.
     *
     * @param srcPassport   Паспорт отправителя
     * @param srcRequisite  Реквизиты счета списания
     * @param destPassport  Паспорт получателя
     * @param destRequisite Реквизиты счета зачисления
     * @param amount        Сумма переводимых средств
     * @return true в случае успешной операции, false в случае неудачи
     */
    public boolean transferMoney(String srcPassport, String srcRequisite,
                                 String destPassport, String destRequisite, double amount) {
        boolean result = false;
        Account srcAccount = findByRequisite(srcPassport, srcRequisite);
        Account destAccount = findByRequisite(destPassport, destRequisite);
        if (!(srcAccount == null || destAccount == null || srcAccount.getBalance() < amount)) {
            srcAccount.setBalance(srcAccount.getBalance() - amount);
            destAccount.setBalance(destAccount.getBalance() + amount);
            result = true;
        }
        return result;
    }

    /**
     * Метод возвращает список всех банковских счетов,
     * ассоциированных с пользователем
     *
     * @param user Объект-пользователь
     * @return Список банковских счетов
     */
    public List<Account> getAccounts(User user) {
        return users.get(user);
    }
}
