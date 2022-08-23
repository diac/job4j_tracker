package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftElements = left.split("/");
        String[] rightElements = right.split("/");
        int result = rightElements[0].compareTo(leftElements[0]);
        return result != 0 ? result : left.compareTo(right);
    }
}
