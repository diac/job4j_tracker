package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class PassportOffice {

    private Map<String, Citizen> citizens = new HashMap<>();

    public boolean add(Citizen citizen) {
        boolean rsl = false;
        if (citizens.get(citizen.getPassport()) == null) {
            citizens.put(citizen.getPassport(), citizen);
        }
        return rsl;
    }

    public Citizen get(String passport) {
        return citizens.get(passport);
    }
}
