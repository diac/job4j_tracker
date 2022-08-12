package ru.job4j.poly;

import static java.lang.Math.ceil;

public class Train extends Vehicle {

    private static final int KILOWATT_HOUR_PRICE = 5;
    private int passengers;
    private float fuel;

    @Override
    public void move() {
        while (fuel > 0) {
            System.out.println("Наш паровоз вперед летит.");
            fuel -= (float) passengers / 80;
        }
        System.out.println("Стоим на запасном пути.");
    }

    @Override
    public void passengers(int count) {
        this.passengers = count;
    }

    @Override
    public int fill(float volume) {
        int price = (int) ceil(volume * KILOWATT_HOUR_PRICE);
        fuel = volume;
        return price;
    }
}
