package ru.job4j.jdbc;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.StringJoiner;

public class TableEditor implements AutoCloseable {

    private Connection connection;

    private Properties properties;

    public TableEditor(Properties properties) {
        this.properties = properties;
        initConnection();
    }

    private void initConnection() {
        try {
            connection = DriverManager.getConnection(
                    properties.getProperty("db_url"),
                    properties.getProperty("db_username"),
                    properties.getProperty("db_password")
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        String sql = String.format(
                "CREATE TABLE IF NOT EXISTS %s();",
                tableName
        );
        executeUpdate(sql);
    }

    public void dropTable(String tableName) {
        String sql = String.format(
                "DROP TABLE IF EXISTS %s;",
                tableName
        );
        executeUpdate(sql);
    }

    public void addColumn(String tableName, String columnName, String type) {
        String sql = String.format(
                "ALTER TABLE %s ADD COLUMN %s %s;",
                tableName,
                columnName,
                type
        );
        executeUpdate(sql);
    }

    public void dropColumn(String tableName, String columnName) {
        String sql = String.format(
                "ALTER TABLE %s DROP COLUMN %s;",
                tableName,
                columnName
        );
        executeUpdate(sql);
    }

    public void renameColumn(String tableName, String columnName, String newColumnName) {
        String sql = String.format(
                "ALTER TABLE %s RENAME COLUMN %s TO %s;",
                tableName,
                columnName,
                newColumnName
        );
        executeUpdate(sql);
    }

    private void executeUpdate(String sql) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getTableScheme(Connection connection, String tableName) throws Exception {
        var rowSeparator = "-".repeat(30).concat(System.lineSeparator());
        var header = String.format("%-15s|%-15s%n", "NAME", "TYPE");
        var buffer = new StringJoiner(rowSeparator, rowSeparator, rowSeparator);
        buffer.add(header);
        try (var statement = connection.createStatement()) {
            var selection = statement.executeQuery(String.format(
                    "select * from %s limit 1", tableName
            ));
            var metaData = selection.getMetaData();
            for (int i = 1; i <= metaData.getColumnCount(); i++) {
                buffer.add(String.format("%-15s|%-15s%n",
                        metaData.getColumnName(i), metaData.getColumnTypeName(i))
                );
            }
        }
        return buffer.toString();
    }

    public static void main(String[] args) throws Exception {
        Properties config = new Properties();
        final String tableName = "exercise_table";
        final String columnName = "test_column";
        final String newColumnName = columnName + "_renamed";
        final String columnType = "VARCHAR(255)";
        try (InputStream in = TableEditor.class.getClassLoader().getResourceAsStream("app.properties")) {
            config.load(in);
        }
        try (TableEditor tableEditor = new TableEditor(config)) {
            tableEditor.createTable(tableName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.addColumn(tableName, columnName, columnType);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.renameColumn(tableName, columnName, newColumnName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.dropColumn(tableName, newColumnName);
            System.out.println(getTableScheme(tableEditor.connection, tableName));
            tableEditor.dropTable(tableName);
        }
    }

    @Override
    public void close() throws Exception {
        if (connection != null) {
            connection.close();
        }
    }
}
