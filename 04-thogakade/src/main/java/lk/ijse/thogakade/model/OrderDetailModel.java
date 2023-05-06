package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/30/23 - 10:53 AM   
*/

import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.CartDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailModel {
    public static boolean save(String oId, List<CartDTO> cartDTOList) throws SQLException {
        for(CartDTO dto : cartDTOList) {
            if(!save(oId, dto)) {
                return false;
            }
        }
        return true;
    }

    private static boolean save(String oId, CartDTO dto) throws SQLException {
        Connection con = DBConnection.getInstance().getConnection();
        String sql = "INSERT INTO OrderDetail(orderId, itemCode, qty, unitPrice)" +
                "VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, oId);
        pstm.setString(2, dto.getCode());
        pstm.setInt(3, dto.getQty());
        pstm.setDouble(4, dto.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }
}
