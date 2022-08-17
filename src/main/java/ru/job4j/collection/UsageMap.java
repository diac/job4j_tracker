package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class UsageMap {

    public static void main(String[] args) {
        HashMap<String, String> map = new HashMap<>();
        map.put("ivanov@mail.ru", "Ivan Ivanov");
        map.put("petrov@mail.ru", "Petr Petrov");
        map.put("smirnoff@mail.ru", "Sergey Smirnov");
        for (Map.Entry<String, String> entry : map.entrySet()) {
            System.out.println(entry);
        }
    }
}
