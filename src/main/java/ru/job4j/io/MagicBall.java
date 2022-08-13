package ru.job4j.io;

import java.util.Random;
import java.util.Scanner;

public class MagicBall {

    public static void main(String[] args) {
        System.out.println("Я великий Оракул. Что ты хочешь узнать?");
        Scanner input = new Scanner(System.in);
        String question = input.nextLine();
        String answer = "Может быть";
        int rand = new Random().nextInt(3);
        if (rand == 0) {
            answer = "Да";
        } else if (rand == 1) {
            answer = "Нет";
        }
        System.out.println();
        System.out.println("Ты меня спрашиваешь \"" + question + "\"...");
        System.out.println("Вот, что я тебе скажу на это:");
        System.out.println(answer);
    }
}
