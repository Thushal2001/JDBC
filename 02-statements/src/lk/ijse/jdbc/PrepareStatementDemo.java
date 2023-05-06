package lk.ijse.jdbc;

/*
    @author DanujaV
    @created 3/7/23 - 2:47 PM   
*/

import java.sql.*;
import java.util.Properties;

public class PrepareStatementDemo {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    private static void deleteCustomer(String id) throws ClassNotFoundException, SQLException {
        // since jdk1.6 & jdbc4 this will be done by automatically
        //        Class.forName("com.mysql.cj.jdbc.Driver");

        // try-with-resource
        try (Connection connection = DriverManager.getConnection(URL, props);) {
            String sql = "DELETE FROM Customer WHERE id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);

            int affectedRows = pstm.executeUpdate();
            System.out.println(affectedRows > 0 ? "deleted!!" : "not delete!");
        }

//        connection.close();   // we don't want anymore
    }

    private static void searchById(String id) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "SELECT * FROM Customer WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();

            if(resultSet.next()) {
                String cus_id = resultSet.getString(1);
                String cus_name = resultSet.getString(2);
                String cus_address = resultSet.getString(3);
                double cus_salary = resultSet.getDouble(4);

                System.out.println(cus_id + " - " + cus_name + " - " + cus_address + " - " + cus_salary);
            }
        }
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        deleteCustomer("C022");

        searchById("C005");
    }
}
