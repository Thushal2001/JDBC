package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/21/23 - 11:33 AM   
*/

import lk.ijse.thogakade.db.DBConnection;

import java.sql.*;
import java.time.LocalDate;

public class OrderModel {
    public static String getNextOrderId() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "SELECT id FROM Orders ORDER BY id DESC LIMIT 1";

        ResultSet resultSet = con.createStatement().executeQuery(sql);

        if (resultSet.next()) {
            return splitOrderId(resultSet.getString(1));
        }
        return splitOrderId(null);
    }

    private static String splitOrderId(String currentId) {
        if(currentId != null) {
            String[] strings = currentId.split("D0");
            int id = Integer.parseInt(strings[1]);
            id++;
            return "D0" + id;
        }
        return "D001";
    }

    public static boolean save(String oId, String cusId, LocalDate date) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "INSERT INTO Orders(id, customerId, date) VALUES(?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, oId);
        pstm.setString(2, cusId);
        pstm.setDate(3, Date.valueOf(date));

        return pstm.executeUpdate() > 0;
    }
}
