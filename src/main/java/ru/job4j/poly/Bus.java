package ru.job4j.poly;

import static java.lang.Math.ceil;

public class Bus extends Vehicle {

    private static final int GAS_LITER_PRICE = 51_95;
    private int passengers;
    private float fuel;

    @Override
    public void move() {
        while (fuel > 0) {
            System.out.println("Едем-едем");
            fuel -= (float) passengers / 40;
        }
        System.out.println("Приехали. Бензин кончился.");
    }

    @Override
    public void passengers(int count) {
        this.passengers = count;
    }

    @Override
    public int fill(float volume) {
        int price = (int) ceil(volume * GAS_LITER_PRICE);
        fuel = volume;
        return price;
    }
}
