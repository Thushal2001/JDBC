package lk.ijse.thogakade.controller;

/*
    @author DanujaV
    @created 3/21/23 - 10:25 AM   
*/

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.thogakade.dto.CartDTO;
import lk.ijse.thogakade.dto.Customer;
import lk.ijse.thogakade.dto.Item;
import lk.ijse.thogakade.dto.tm.CartTM;
import lk.ijse.thogakade.model.CustomerModel;
import lk.ijse.thogakade.model.ItemModel;
import lk.ijse.thogakade.model.OrderModel;
import lk.ijse.thogakade.model.PlaceOrderModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class PlaceOrderFormController implements Initializable {
    @FXML
    private JFXComboBox<String> cmbCustomerId;

    @FXML
    private JFXComboBox<String> cmbItemCode;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colItemCode;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private Label lblCustomerName;

    @FXML
    private Label lblDescription;

    @FXML
    private Label lblOrderDate;

    @FXML
    private Label lblOrderId;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<CartTM> tblOrderCart;

    @FXML
    private TextField txtQty;

    @FXML
    private Label lblNetTotal;

    private ObservableList<CartTM> obList = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCellValueFactory();
        generateNextOrderId();
        setOrderDate();
        loadCustomerIds();
        loadItemCodes();
    }

    void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {
        try {
            String id = OrderModel.getNextOrderId();
            lblOrderId.setText(id);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void loadItemCodes() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> codes = ItemModel.loadCodes();

            for (String code : codes) {
                obList.add(code);
            }
            cmbItemCode.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }

    }

    private void loadCustomerIds() {
        try {
            ObservableList<String> obList = FXCollections.observableArrayList();
            List<String> ids = CustomerModel.loadIds();

            for (String id : ids) {
                obList.add(id);
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void setOrderDate() {
        lblOrderDate.setText(String.valueOf(LocalDate.now()));
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        String description = lblDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        Button btnRemove = new Button("Remove");
        btnRemove.setCursor(Cursor.HAND);

        setRemoveBtnOnAction(btnRemove); /* set action to the btnRemove */

        if (!obList.isEmpty()) {
            for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
                if (colItemCode.getCellData(i).equals(code)) {
                    qty += (int) colQty.getCellData(i);
                    total = qty * unitPrice;

                    obList.get(i).setQty(qty);
                    obList.get(i).setTotal(total);

                    tblOrderCart.refresh();
                    calculateNetTotal();
                    return;
                }
            }
        }

        CartTM tm = new CartTM(code, description, qty, unitPrice, total, btnRemove);

        obList.add(tm);
        tblOrderCart.setItems(obList);
        calculateNetTotal();

        txtQty.setText("");
    }

    private void calculateNetTotal() {
        double netTotal = 0.0;
        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            double total = (double) colTotal.getCellData(i);
            netTotal += total;
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");

        stage.centerOnScreen();
    }

    private void setRemoveBtnOnAction(Button btn) {
        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> result = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if (result.orElse(no) == yes) {

                int index = tblOrderCart.getSelectionModel().getSelectedIndex();
                obList.remove(index);

                tblOrderCart.refresh();
                calculateNetTotal();
            }

        });
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = new Stage();
        stage.setTitle("Customer Manage");
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) {
        String oId = lblOrderId.getText();
        String cusId = cmbCustomerId.getValue();

        List<CartDTO> cartDTOList = new ArrayList<>();

        for (int i = 0; i < tblOrderCart.getItems().size(); i++) {
            CartTM cartTM = obList.get(i);

            CartDTO dto = new CartDTO(
                    cartTM.getCode(),
                    cartTM.getQty(),
                    cartTM.getUnitPrice()
            );
            cartDTOList.add(dto);
        }

        boolean isPlaced = false;
        try {
            isPlaced = PlaceOrderModel.placeOrder(oId, cusId, cartDTOList);
            if(isPlaced) {
                new Alert(Alert.AlertType.CONFIRMATION, "Order Placed").show();
            } else {
                new Alert(Alert.AlertType.ERROR, "Order Not Placed").show();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error").show();
        }
    }

    @FXML
    void cmbCustomerOnAction(ActionEvent event) {
        String id = cmbCustomerId.getValue();

        try {
            Customer customer = CustomerModel.searchById(id);
            lblCustomerName.setText(customer.getName());
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    @FXML
    void cmbItemOnAction(ActionEvent event) {
        String code = cmbItemCode.getValue();
        try {
            Item item = ItemModel.searchById(code);
            fillItemFields(item);

            txtQty.requestFocus();
        } catch (SQLException e) {
            e.printStackTrace();
            new Alert(Alert.AlertType.ERROR, "SQL Error!").show();
        }
    }

    private void fillItemFields(Item item) {
        lblDescription.setText(item.getDescription());
        lblUnitPrice.setText(String.valueOf(item.getUnitPrice()));
        lblQtyOnHand.setText(String.valueOf(item.getQtyOnHand()));
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }
}
