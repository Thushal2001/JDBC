package lk.ijse.thogakade.controller;

/*
    @author DanujaV
    @created 3/14/23 - 9:05 AM   
*/

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class DashboardFormController {
    @FXML
    private AnchorPane root;

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));

        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage)root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();

        /*AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = new Stage();

        stage.setScene(scene);
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();

        stage.show();*/
    }

    @FXML
    void btnItemOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/item_form.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Item Manage");
        stage.setScene(scene);
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader
                .load(getClass()
                        .getResource("/view/place_order_form.fxml"));

        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Place order");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
}
