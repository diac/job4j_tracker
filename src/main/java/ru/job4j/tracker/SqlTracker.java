package ru.job4j.tracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlTracker implements Store, AutoCloseable {

    private final Connection cn;

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
        }
    }

    @Override
    public Item add(Item item) {
        try (
                PreparedStatement statement = cn.prepareStatement(
                        "INSERT INTO items(name, created) VALUES (?, ?);",
                        Statement.RETURN_GENERATED_KEYS
                )
        ) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
            statement.execute();
            try (ResultSet resultSet = statement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    item.setId(resultSet.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    @Override
    public boolean replace(int id, Item item) {
        boolean result = false;
        try (
                PreparedStatement statement = cn.prepareStatement(
                        "UPDATE items SET name = ?, created = ? WHERE id = ?;"
                )
        ) {
            statement.setString(1, item.getName());
            statement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            statement.setInt(3, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean delete(int id) {
        boolean result = false;
        try (
                PreparedStatement statement = cn.prepareStatement(
                        "DELETE FROM items WHERE id = ?;"
                )
        ) {
            statement.setInt(1, id);
            result = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Item> findAll() {
        List<Item> items = new ArrayList<>();
        try (Statement statement = cn.createStatement()) {
            var selection = statement.executeQuery("SELECT * FROM items;");
            while (selection.next()) {
                items.add(
                        new Item(
                                selection.getInt(1),
                                selection.getString(2),
                                selection.getTimestamp(3).toLocalDateTime()
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public List<Item> findByName(String key) {
        List<Item> items = new ArrayList<>();
        try (
                PreparedStatement statement = cn.prepareStatement("SELECT * FROM items WHERE name LIKE ?;")
        ) {
            statement.setString(1, key);
            var selection = statement.executeQuery();
            while (selection.next()) {
                items.add(
                        new Item(
                                selection.getInt(1),
                                selection.getString(2),
                                selection.getTimestamp(3).toLocalDateTime()
                        )
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
    public Item findById(int id) {
        Item item = null;
        try (
                PreparedStatement statement = cn.prepareStatement(
                        "SELECT * FROM items WHERE id = ?;"
                )
        ) {
            statement.setInt(1, id);
            var selection = statement.executeQuery();
            if (selection.next()) {
                item = new Item(
                        selection.getInt(1),
                        selection.getString(2),
                        selection.getTimestamp(3).toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
