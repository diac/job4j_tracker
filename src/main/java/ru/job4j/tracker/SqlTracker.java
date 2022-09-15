package ru.job4j.tracker;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SqlTracker implements Store, AutoCloseable {

    private Connection cn;

    public SqlTracker() {

    }

    public SqlTracker(Connection cn) {
        this.cn = cn;
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException();
        }
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
            statement.setTimestamp(2, Timestamp.valueOf(item.getCreated()));
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
                items.add(fromResultSet(selection));
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
                items.add(fromResultSet(selection));
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
                item = fromResultSet(selection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

    private Item fromResultSet(ResultSet resultset) throws SQLException {
        return new Item(
                resultset.getInt(1),
                resultset.getString(2),
                resultset.getTimestamp(3).toLocalDateTime()
        );
    }
}
