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
        items.sort(new ItemAscByName());
        assertThat(items).isEqualTo(expected);
    }
}