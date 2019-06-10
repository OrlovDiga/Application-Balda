package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logicApplication.WorkWithOptions;

import java.io.IOException;


public class OptionsWindowController {

    WorkWithOptions workWithOptions = new WorkWithOptions();

    @FXML
    Button strtWndwBut;
    @FXML
    TextField fieldTimePlay;
    @FXML
    TextField fieldNameFirst;
    @FXML
    TextField fieldNameSec;



    @FXML
    void returnStartWindow() {
        Stage stage = (Stage) strtWndwBut.getScene().getWindow();
        stage.close();
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource("../fxmlFiles/StartWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Приложение");
        assert root1 != null;
        stage.setScene(new Scene(root1));
        stage.show();
    }


    @FXML
    void changeTimePlay() {
        workWithOptions.changeTime(fieldTimePlay, "timePlay");
    }


    @FXML
    void changeNameFirst() { workWithOptions.changeName(fieldNameFirst, "nameFirst");
    }


    @FXML
    void changeNameSecond() {
        workWithOptions.changeName(fieldNameSec, "nameSecond");
    }


}
