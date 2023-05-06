package lk.ijse.thogakade.model;

/*
    @author DanujaV
    @created 3/30/23 - 10:21 AM   
*/

import lk.ijse.thogakade.db.DBConnection;
import lk.ijse.thogakade.dto.CartDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class PlaceOrderModel {
    public static boolean placeOrder(String oId, String cusId, List<CartDTO> cartDTOList) throws SQLException {
        Connection con = null;
        try {
            con = DBConnection.getInstance().getConnection();
            con.setAutoCommit(false);

            boolean isSaved = OrderModel.save(oId, cusId, LocalDate.now());
            if(isSaved) {
                boolean isUpdated = ItemModel.updateQty(cartDTOList);
                if(isUpdated) {
                    boolean isOrdered = OrderDetailModel.save(oId, cartDTOList);
                    if(isOrdered) {
                        con.commit();
                        return true;
                    }
                }
            }
            return false;
        } catch (SQLException er) {
            con.rollback();
            return false;
        } finally {
            System.out.println("finally");
            con.setAutoCommit(true);
        }

    }
}
