package ru.job4j.oop;

public class BallStory {

    public static void main(String[] args) {
        Hare hare = new Hare();
        Wolf wolf = new Wolf();
        Fox fox = new Fox();
        Ball ball = new Ball();
        System.out.println("���������� ������� �����.");
        hare.tryEat(ball);
        System.out.println("���������� ������� �����.");
        wolf.tryEat(ball);
        System.out.println("���������� ������� ������.");
        fox.tryEat(ball);
    }
}
