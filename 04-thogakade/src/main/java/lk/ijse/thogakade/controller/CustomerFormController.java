package lk.ijse.thogakade.controller;

/*
    @author DanujaV
    @created 3/14/23 - 9:37 AM   
*/

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.dto.Customer;
import lk.ijse.thogakade.dto.tm.CustomerTM;
import lk.ijse.thogakade.model.CustomerModel;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;

public class CustomerFormController implements Initializable {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "1234");
    }

    public TableView<CustomerTM> tblCustomer;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;


    @Override
    public void initialize(java.net.URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        getAll();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));
    }

    private void getAll() {
        try {
            ObservableList<CustomerTM> obList = FXCollections.observableArrayList();
            List<Customer> cusList = CustomerModel.getAll();

            for (Customer customer : cusList) {
                obList.add(new CustomerTM(
                        customer.getId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getSalary()
                ));
            }
            tblCustomer.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Dashboard");
        stage.setScene(new Scene(parent));
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "DELETE FROM Customer WHERE id = ?";

            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            if (pstm.executeUpdate() > 0) {
                new Alert(Alert.AlertType.CONFIRMATION, "deletd!").show();
            }
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "INSERT INTO Customer(id, name, address, salary)" +
                    "VALUES(?, ?, ?, ?)";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);
            pstm.setString(2, name);
            pstm.setString(3, address);
            pstm.setDouble(4, salary);

            int affectedRows = pstm.executeUpdate();
            if (affectedRows > 0) {
                new Alert(Alert.AlertType.CONFIRMATION,
                        "huree!! customer added :)")
                        .show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();
        String name = txtName.getText();
        String address = txtAddress.getText();
        double salary = Double.parseDouble(txtSalary.getText());

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "UPDATE Customer SET name = ?, address = ?, salary = ? WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, name);
            pstm.setString(2, address);
            pstm.setDouble(3, salary);
            pstm.setString(4, id);

            boolean isUpdated = pstm.executeUpdate() > 0;
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "yes! updated!!").show();
            }
        }
    }

    @FXML
    void codeSearchOnAction(ActionEvent event) throws SQLException {
        String id = txtId.getText();

        try (Connection con = DriverManager.getConnection(URL, props)) {
            String sql = "SELECT * FROM Customer WHERE id = ?";
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, id);

            ResultSet resultSet = pstm.executeQuery();
            if (resultSet.next()) {
                String cus_id = resultSet.getString(1);
                String cus_name = resultSet.getString(2);
                String cus_address = resultSet.getString(3);
                double cus_salary = resultSet.getDouble(4);

                txtId.setText(cus_id);
                txtName.setText(cus_name);
                txtAddress.setText(cus_address);
                txtSalary.setText(String.valueOf(cus_salary));
            }
        }
    }

}
