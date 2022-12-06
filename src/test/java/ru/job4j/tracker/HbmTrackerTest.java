package ru.job4j.tracker;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.persistence.NoResultException;

import static org.assertj.core.api.Assertions.*;

public class HbmTrackerTest {

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            Item result = tracker.findById(item.getId());
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenReplaceItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            item.setName("test_updated");
            tracker.replace(item.getId(), item);
            Item itemInDb = tracker.findById(item.getId());
            assertThat("test_updated").isEqualTo(itemInDb.getName());
        }
    }

    @Test
    public void whenDeleteItem() throws Exception {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            int itemId = item.getId();
            tracker.delete(itemId);
            Assertions.assertThrows(
                    NoResultException.class,
                    () -> tracker.findById(itemId)
            );
        }
    }
}