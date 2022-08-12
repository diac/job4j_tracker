package ru.job4j.poly;

import static java.lang.Math.ceil;

public class Bus implements Transport {

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

    public static void main(String[] args) {
        Bus bus = new Bus();
        String price = String.valueOf((float) bus.fill(326.43f) / 100);
        System.out.println("Заправились на " + price + " рублей");
        bus.passengers(20);
        bus.move();
    }
}
