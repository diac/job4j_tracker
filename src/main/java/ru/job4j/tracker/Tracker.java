package ru.job4j.tracker;

import java.util.Arrays;

public class Tracker {
    private final Item[] items = new Item[100];
    private int ids = 1;
    private int size = 0;

    public Item add(Item item) {
        item.setId(ids++);
        items[size++] = item;
        return item;
    }

    public Item[] findAll() {
        Item[] result = new Item[items.length];
        int resultSize = 0;
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null) {
                result[resultSize++] = items[i];
            }
        }
        return Arrays.copyOf(result, resultSize);
    }

    public Item[] findByName(String key) {
        Item[] result = new Item[items.length];
        int resultSize = 0;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getName().equals(key)) {
                result[resultSize++] = item;
            }
        }
        return Arrays.copyOf(result, resultSize);
    }

    public Item findById(int id) {
        Item rsl = null;
        for (int index = 0; index < size; index++) {
            Item item = items[index];
            if (item.getId() == id) {
                rsl = item;
                break;
            }
        }
        return rsl;
    }
}