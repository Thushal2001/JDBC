package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/7/23 - 10:23 AM   
*/

import com.mysql.cj.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCConfigDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    private static Connection connection;
    private static Statement statement;
    private static void loadRegisterDriver() throws ClassNotFoundException {
//        DriverManager.registerDriver(new Driver());
        Class.forName("com.mysql.cj.jdbc.Driver");
    }

    private static void establishedConnection() throws SQLException {
        /*connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/ThogaKade",
                "root",
                "Danu25412541@"
        );*/
        connection = DriverManager.getConnection(URL, props);
    }

    private static void createStatement() throws SQLException {
        statement = connection.createStatement();
    }

    private static void executeSQLQuery() throws SQLException {
        String sql = "CREATE TABLE temp(id INTEGER)";
        int affectedRows = statement.executeUpdate(sql);
        System.out.println("affectedRows: " + affectedRows);
    }

    private static void closeTheConnection() throws SQLException {
        connection.close();
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        loadRegisterDriver();   //1
        establishedConnection();    //2
        createStatement();  //3
        executeSQLQuery();  //4
        closeTheConnection();   //5
    }
}
