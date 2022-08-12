package ru.job4j.io;

import java.util.Scanner;

public class Matches {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("���� 11.");
        boolean turn = true;
        int count = 11;
        while (count > 0) {
            String player = turn ? "������ ����� " : "������ �����";
            int matches;
            boolean legalMove;
            do {
                System.out.println("�������� ������: " + count);
                if (count >= 3) {
                    System.out.println(player + " ������� ����� �� 1 �� 3:");
                } else if (count == 2) {
                    System.out.println(player + " ������� ����� �� 1 �� 2:");
                } else {
                    System.out.println(player + ", ������� 1, ����� ��������!");
                }
                matches = Integer.parseInt(input.nextLine());
                legalMove = (matches <= count) && (matches >= 1) && (matches <= 3);
                if (!legalMove) {
                    System.out.println("�������� ���!");
                }
            } while (!legalMove);
            turn = !turn;
            count -= matches;
        }
        if (!turn) {
            System.out.println("������� ������ �����");
        } else {
            System.out.println("������� ������ �����");
        }
    }
}
