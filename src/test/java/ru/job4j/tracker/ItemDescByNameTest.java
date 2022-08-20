package ru.job4j.tracker;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ItemDescByNameTest {

    @Test
    public void whenSortDesc() {
        List<Item> items = Arrays.asList(
                new Item("Coding"),
                new Item("Troubleshooting"),
                new Item("Debug"),
                new Item("Abstract"),
                new Item("Refactoring")
        );
        List<Item> expected = Arrays.asList(
                new Item("Troubleshooting"),
                new Item("Refactoring"),
                new Item("Debug"),
                new Item("Coding"),
                new Item("Abstract")
        );
        items.sort(new ItemDescByName());
        assertThat(items).isEqualTo(expected);
    }
}