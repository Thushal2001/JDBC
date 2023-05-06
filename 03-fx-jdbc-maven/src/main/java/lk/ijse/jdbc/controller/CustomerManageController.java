package lk.ijse.jdbc.controller;

/*
    @author DanujaV
    @created 3/7/23 - 3:48 PM   
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class CustomerManageController {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }
    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        try (Connection connection = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Customer(id, name, address, salary)" +
                    "VALUES(?, ?, ?, ?)";

            PreparedStatement pstm = connection.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setDouble(4, salary);

            int affectedRows = pstm.executeUpdate();

            if(affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "Customer Added!").show();
            }
        }
    }
}
