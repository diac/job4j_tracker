package ru.job4j.poly;

public class Vehicle implements Transport {

    @Override
    public void move() {
        System.out.println("Поехали!");
    }

    @Override
    public void passengers(int count) {

    }

    @Override
    public int fill(float volume) {
        return 0;
    }
}
