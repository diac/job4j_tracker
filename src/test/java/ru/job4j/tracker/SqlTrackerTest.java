package ru.job4j.tracker;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class SqlTrackerTest {
    private static Connection connection;

    @BeforeClass
    public static void initConnection() {
        try (InputStream in = SqlTrackerTest.class.getClassLoader().getResourceAsStream("test.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")

            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @AfterClass
    public static void closeConnection() throws SQLException {
        connection.close();
    }

    @After
    public void wipeTable() throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement("delete from items")) {
            statement.execute();
        }
    }

    @Test
    public void whenSaveItemAndFindByGeneratedIdThenMustBeTheSame() {
         SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertThat(item).isEqualTo(tracker.findById(item.getId()));
    }

    @Test
    public void whenAddNewItemAndUpdateThenSuccess() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = new Item("item");
        tracker.add(item);
        assertEquals(tracker.findById(item.getId()), item);
        tracker.replace(item.getId(), new Item("Updated item"));
        Item updatedItem = tracker.findById(item.getId());
        assertEquals("Updated item", updatedItem.getName());
    }

    @Test
    public void whenFindAll() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> items = List.of(
                new Item("item_1"),
                new Item("item_2"),
                new Item("item_3"),
                new Item("item_4"),
                new Item("item_5")
        );
        tracker.add(items.get(0));
        tracker.add(items.get(1));
        tracker.add(items.get(2));
        tracker.add(items.get(3));
        tracker.add(items.get(4));
        List<Item> foundItems = tracker.findAll();
        assertThat(foundItems).isEqualTo(items);
    }

    @Test
    public void whenFindByName() {
        SqlTracker tracker = new SqlTracker(connection);
        List<Item> items = List.of(
                new Item("item_1"),
                new Item("item_2"),
                new Item("item_3"),
                new Item("item_4"),
                new Item("item_5")
        );
        tracker.add(items.get(0));
        tracker.add(items.get(1));
        tracker.add(items.get(2));
        tracker.add(items.get(3));
        tracker.add(items.get(4));
        List<Item> foundItems = tracker.findByName("item%");
        assertThat(foundItems).isEqualTo(items);
    }

    @Test
    public void whenDelete() {
        SqlTracker tracker = new SqlTracker(connection);
        Item item = tracker.add(new Item("item"));
        tracker.delete(item.getId());
        assertThat(tracker.findById(item.getId())).isNull();
    }
}