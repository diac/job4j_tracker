package ru.job4j.oop;

public class Jukebox {

    public static void music(int position) {
        if (position == 1) {
            System.out.println("����� ����� ��������");
        } else if (position == 2) {
            System.out.println("��������� ����");
        } else {
            System.out.println("����� �� �������");
        }
    }

    public static void main(String[] args) {
        music(1);
        music(2);
        music(100);
    }
}
