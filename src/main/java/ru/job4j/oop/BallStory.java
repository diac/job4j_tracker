package ru.job4j.oop;

public class BallStory {

    public static void main(String[] args) {
        Hare hare = new Hare();
        Wolf wolf = new Wolf();
        Fox fox = new Fox();
        Ball ball = new Ball();
        System.out.println("Повстречал Колобок Зайца.");
        hare.tryEat(ball);
        System.out.println("Повстречал Колобок Волка.");
        wolf.tryEat(ball);
        System.out.println("Повстречал Колобок Лисицу.");
        fox.tryEat(ball);
    }
}
