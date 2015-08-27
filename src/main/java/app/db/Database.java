package app.db;

import app.Collector;
import app.Command;
import app.pojo.Employee;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Database {

    private Connection connection;

    public Database(String name) {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + name);
            System.out.println("Opened database successfully");
            System.out.println("****************************");
        } catch (Exception e) {
        }

        init();
    }

    private void init() {
        try {
            if (!getData().isEmpty()) {
                return;
            }
        } catch (SQLException ex) {
        }

        try {
            createTable();
        } catch (SQLException e) {
            System.out.println("Unable to create table. Maybe it already exists.");
            return;
        }

        try {
            createData();
        } catch (SQLException ex) {
        }
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        String sql = "CREATE TABLE EMPLOYEE "
                + "(ID             INTEGER PRIMARY KEY      NOT NULL,"
                + " NAME           TEXT                     NOT NULL, "
                + " AGE            INT                      NOT NULL, "
                + " ADDRESS        CHAR(50), "
                + " SALARY         REAL)";
        stmt.executeUpdate(sql);
        stmt.close();
        System.out.println("Table created successfully");
    }

    public void createData() throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        String sql = "INSERT INTO EMPLOYEE (NAME,AGE,ADDRESS,SALARY) "
                + "VALUES ('Paul', 32, 'California', 20000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEE (NAME,AGE,ADDRESS,SALARY) "
                + "VALUES ('Allen', 25, 'Texas', 15000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEE (NAME,AGE,ADDRESS,SALARY) "
                + "VALUES ('Teddy', 23, 'Norway', 20000.00 );";
        stmt.executeUpdate(sql);

        sql = "INSERT INTO EMPLOYEE (NAME,AGE,ADDRESS,SALARY) "
                + "VALUES ('Mark', 25, 'Rich-Mond ', 65000.00 );";
        stmt.executeUpdate(sql);

        stmt.close();
        connection.commit();
    }

    public void update(String sql) throws SQLException {
        connection.setAutoCommit(false);
        Statement stmt = connection.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
        connection.commit();
    }

    public void query(String query, Command com) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            com.act(rs);
        }
        rs.close();
        stmt.close();
    }

    public <T> List<T> queryAndCollect(String query, Collector col) throws SQLException {
        List<T> rows = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            rows.add((T) col.collect(rs));
        }
        rs.close();
        stmt.close();
        return rows;
    }

    public List<Employee> getData() throws SQLException {
        return queryAndCollect("SELECT * FROM EMPLOYEE;", rs -> {
            return new Employee(
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("address"),
                    rs.getFloat("salary"));
        });
    }
}
