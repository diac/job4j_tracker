package ru.job4j.oop;

public class Calculator {

    private static int x = 5;

    public static int sum(int y) {
        return x + y;
    }

    public int multiply(int a) {
        return x * a;
    }

    public static int minus(int a) {
        return a - x;
    }

    public int divide(int a) {
        return a / x;
    }

    public int sumAllOperation(int a) {
        return sum(a) + multiply(a) + minus(a) + divide(a);
    }

    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        int sum = Calculator.sum(10);
        System.out.println(sum);
        int mul = calculator.multiply(5);
        System.out.println(mul);
        int minus = Calculator.minus(3);
        System.out.println(minus);
        int div = calculator.divide(35);
        System.out.println(div);
        int sumAll = calculator.sumAllOperation(100);
        System.out.println(sumAll);
    }
}
