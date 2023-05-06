package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/14/23 - 2:27 PM   
*/

import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.CartDTO;
import lk.ijse.thogakade.dto.Item;
import lk.ijse.thogakade.util.CrudUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemModel {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    /*public static boolean save(String code, String description, double unitPrice, int qtyOnHand) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Item(code, description, unitPrice, qtyOnHand) " +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);
            pstm.setString(2, description);
            pstm.setDouble(3, unitPrice);
            pstm.setInt(4, qtyOnHand);

            int affectedRows = pstm.executeUpdate();
            if(affectedRows > 0) {
                return true;
            } else {
                return false;
            }
        }
    }*/

    public static boolean update(String code, String description, double unitPrice, int qtyOnHand) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Item SET description = ?, unitPrice = ?, " +
                    "qtyOnHand = ? WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, description);
            pstm.setDouble(2, unitPrice);
            pstm.setInt(3, qtyOnHand);
            pstm.setString(4, code);

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean delete(String code) throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "DELETE FROM Item WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);

            return pstm.executeUpdate() > 0;
        }
    }

    public static boolean save(Item item) throws SQLException {
        String sql = "INSERT INTO Item(code, description, unitPrice, qtyOnHand) " +
                "VALUES(?, ?, ?, ?)";
        return CrudUtil.execute(
                sql,
                item.getCode(),
                item.getDescription(),
                item.getUnitPrice(),
                item.getQtyOnHand());

       /* try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Item(code, description, unitPrice, qtyOnHand) " +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, item.getCode());
            pstm.setString(2, item.getDescription());
            pstm.setDouble(3, item.getUnitPrice());
            pstm.setInt(4, item.getQtyOnHand());

            return pstm.executeUpdate() > 0;
        }*/
    }

    public static List<Item> getAll() throws SQLException {
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "SELECT * FROM Item";
            PreparedStatement pstm = con.prepareStatement(sql);

            ResultSet resultSet = pstm.executeQuery();

            List<Item> dataList = new ArrayList<>();

            while (resultSet.next()) {
                String code = resultSet.getString(1);
                String description = resultSet.getString(2);
                Double unitPrice = resultSet.getDouble(3);
                Integer qtyOnHand = resultSet.getInt(4);

                Item item = new Item(code, description, unitPrice, qtyOnHand);
                dataList.add(item);
            }
            return dataList;
        }
    }

    public static Item search(String code) throws SQLException {
        try (Connection con = DBConnection.getInstance().getConnection()) {
            String sql = "SELECT * FROM Item WHERE code = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, code);

            ResultSet resultSet = pstm.executeQuery();
            if(resultSet.next()) {
                return new Item(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4)
                );
            }
            return null;
        }
    }

    public static List<String> loadCodes() throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        ResultSet resultSet = con.createStatement().executeQuery("SELECT code FROM Item");

        List<String> data =new ArrayList<>();

        while (resultSet.next()) {
            data.add(resultSet.getString(1));
        }
        return data;
    }

    public static Item searchById(String code) throws SQLException {
        PreparedStatement pstm = DBConnection.getInstance().getConnection()
                .prepareStatement("SELECT * FROM Item WHERE code = ?");
        pstm.setString(1, code);
        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            return new Item(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getDouble(3),
                    resultSet.getInt(4)
            );
        }
        return null;
    }

    public static boolean updateQty(List<CartDTO> cartDTOList) throws SQLException {
        for(CartDTO dto : cartDTOList) {
            if(!updateQty(dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(CartDTO dto) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();

        String sql = "UPDATE Item SET qtyOnHand = (qtyOnHand - ?) WHERE code = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setInt(1, dto.getQty());
        pstm.setString(2, dto.getCode());

        return pstm.executeUpdate() > 0;
    }
}