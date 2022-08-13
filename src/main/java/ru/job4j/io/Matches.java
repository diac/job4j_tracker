package ru.job4j.io;

import java.util.Scanner;

public class Matches {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Игра 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "Первый игрок " : "Второй игрок";
            int matches;
            do {
                System.out.println("Осталось спичек: " + count);
                if (count >= 3) {
                    System.out.println(player + " введите число от 1 до 3:");
                } else if (count == 2) {
                    System.out.println(player + " введите число от 1 до 2:");
                } else {
                    System.out.println(player + ", введите 1, чтобы победить!");
                }
                matches = Integer.parseInt(input.nextLine());
                if (!((matches <= count) && (matches >= 1) && (matches <= 3))) {
                    System.out.println("Неверный ход!");
                }
            } while (!((matches <= count) && (matches >= 1) && (matches <= 3)));
            turn = !turn;
            count -= matches;
        }
        if (!turn) {
            System.out.println("Выиграл первый игрок");
        } else {
            System.out.println("Выиграл второй игрок");
        }
    }
}
