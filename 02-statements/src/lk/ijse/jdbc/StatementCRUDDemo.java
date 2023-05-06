package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/7/23 - 11:27 AM   
*/

import java.sql.*;
import java.util.Properties;

public class StatementCRUDDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    private static void insertCustomer() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        Connection connection = DriverManager.getConnection(
                URL,
                props
        );

        Statement statement = connection.createStatement();
        String sql = "INSERT INTO Customer(id, name, address, salary)" +
                "VALUES(\"C025\", \"Dewmith\", \"Rathgama\", \"2000\")";

        int affectedRows = statement.executeUpdate(sql);
        System.out.println("affectedRows: " + affectedRows);

        connection.close();
    }

    private static void updateCustomer() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, props);
        Statement statement = connection.createStatement();

        String sql = "UPDATE Customer SET name = \"deshan\", address = \"Jaffna\", salary = 23223 WHERE id = \"C024\"";
        int affectedRows = statement.executeUpdate(sql);
        System.out.println(affectedRows > 0 ? "updated!!" : "not updated!");

        connection.close();
    }

    private static void deleteCustomer() throws ClassNotFoundException, SQLException {
        // DELETE FROM Customer WHERE id = "C025";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, props);

        String sql = "DELETE FROM Customer WHERE id = \"C025\"";
        Statement statement = connection.createStatement();
        int affectedRows = statement.executeUpdate(sql);

        System.out.println(affectedRows > 0 ? "deleted!!" : "not delete!");

        connection.close();
    }

    private static void searchById() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, props);
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM Customer WHERE id = \"C002\"";

        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            double salary = resultSet.getDouble(4);

            System.out.println(id + " - " + name + " - " + address + " - " + salary);
        }
        connection.close();

    }

    private static void searchAll() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(URL, props);
        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM Customer";

        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            String id = resultSet.getString(1);
            String name = resultSet.getString(2);
            String address = resultSet.getString(3);
            double salary = resultSet.getDouble(4);

            System.out.println(id + " - " + name + " - " + address + " - " + salary);
        }
        connection.close();

        /*resultSet.next();

        String id = resultSet.getString(1);
        String name = resultSet.getString(2);
        String address = resultSet.getString(3);
        double salary = resultSet.getDouble(4);

        System.out.println(id + " - " + name + " - " + address + " - " + salary);

        resultSet.next();
        String id2 = resultSet.getString(1);
        String name2 = resultSet.getString(2);
        String address2 = resultSet.getString(3);
        double salary2 = resultSet.getDouble(4);

        System.out.println(id2 + " - " + name2 + " - " + address2 + " - " + salary2);*/
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        insertCustomer();

        updateCustomer();

        deleteCustomer();

//        searchById();

        searchAll();
    }

}
