package lk.ijse.thogakade;

/*
    @author DanujaV
    @created 3/14/23 - 9:28 AM   
*/

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Launcher extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Parent parent =  FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        stage.setScene(new Scene(parent));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();

        stage.show();
    }
}
