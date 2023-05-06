/*
    @author DanujaV
    @created 3/7/23 - 3:55 PM   
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Launcher extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        URL resource = Launcher.class.getResource("lk.ijse.jdbc/view/customer_manage.fxml");
        Parent load = FXMLLoader.load(resource);

        stage.setScene(new Scene(load));
        stage.setTitle("Customer Manage");
        stage.centerOnScreen();
        stage.show();
    }
}
