package ru.job4j.poly;

import static java.lang.Math.ceil;

public class Plane extends Vehicle {

    private static final int KEROSENE_LITER_PRICE = 61_36;
    private int passengers;
    private float fuel;

    @Override
    public void move() {
        while (fuel > 0) {
            System.out.println("Летим высоко.");
            fuel -= (float) passengers / 80;
        }
        System.out.println("Пикируем.");
    }

    @Override
    public void passengers(int count) {
        this.passengers = count;
    }

    @Override
    public int fill(float volume) {
        int price = (int) ceil(volume * KEROSENE_LITER_PRICE);
        fuel = volume;
        return price;
    }
}
