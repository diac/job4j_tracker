package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemAscByNameTest {

    @Test
    public void whenSortAsc() {
        List<Item> items = Arrays.asList(
                new Item("Coding"),
                new Item("Troubleshooting"),
                new Item("Debug"),
                new Item("Abstract"),
                new Item("Refactoring")
        );
        List<Item> expected = Arrays.asList(
                new Item("Abstract"),
                new Item("Coding"),
                new Item("Debug"),
                new Item("Refactoring"),
                new Item("Troubleshooting")
        );
        boolean result = (
                items.get(0).equals(expected.get(0))
                    && items.get(1).equals(expected.get(1))
                    && items.get(2).equals(expected.get(2))
                    && items.get(3).equals(expected.get(3))
                    && items.get(4).equals(expected.get(4))
                );
        assertThat(result).isTrue();
    }
}