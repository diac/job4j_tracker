package ru.job4j.collection;

import java.util.Comparator;

public class DepDescComp implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String[] leftElements = left.split("/");
        String[] rightElements = right.split("/");
        int result = 0;
        if (leftElements.length != 0 && rightElements.length != 0) {
            result = rightElements[0].compareTo(leftElements[0]);
        }
        if (result == 0) {
            result = Integer.compare(leftElements.length, rightElements.length);
        }
        int minSize = Math.min(leftElements.length, rightElements.length);
        for (int i = 1; i < minSize; i++) {
            if (result != 0) {
                break;
            } else if (leftElements[i].compareTo(rightElements[i]) != 0) {
                result = leftElements[i].compareTo(rightElements[i]);
                break;
            }
        }
        return result;
    }
}
