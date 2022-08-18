package ru.job4j.collection;

import java.util.Comparator;

public class LexSort implements Comparator<String> {

    @Override
    public int compare(String left, String right) {
        String pattern = "\\.\s";
        String[] leftSplit = left.split(pattern);
        String[] rightSplit = right.split(pattern);
        int leftNumber = Integer.parseInt(leftSplit[0]);
        int rightNumber = Integer.parseInt(rightSplit[0]);
        return Integer.compare(leftNumber, rightNumber);
    }
}
