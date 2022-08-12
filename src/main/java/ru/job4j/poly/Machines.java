package ru.job4j.poly;

public class Machines {

    public static void main(String[] args) {
        Vehicle[] vehicles = new Vehicle[5];
        vehicles[0] = new Bus();
        vehicles[0].passengers(10);
        vehicles[0].fill(400);
        vehicles[1] = new Bus();
        vehicles[1].passengers(20);
        vehicles[1].fill(650);
        vehicles[2] = new Bus();
        vehicles[2].passengers(40);
        vehicles[2].fill(1000);
        vehicles[3] = new Train();
        vehicles[3].passengers(800);
        vehicles[3].fill(1000000);
        vehicles[4] = new Plane();
        vehicles[4].passengers(150);
        vehicles[4].fill(10000);
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
        }
    }
}
