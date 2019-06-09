package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class RecordsWindowController implements Initializable {
    @FXML
    Button harkBackButton;

    @FXML
    TextArea recordsList;


    @FXML
    void actionHarkBack() throws IOException {
        Stage stage = (Stage) harkBackButton.getScene().getWindow();
        stage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource("../fxmlFiles/StartWindow.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Приложение");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
