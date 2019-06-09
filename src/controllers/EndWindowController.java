package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logicApplication.WorkWithDB;

import java.io.IOException;

public class EndWindowController {

    @FXML
    Label pointWinner;
    @FXML
    Label nameWinner;


    @FXML
    void doNewGame() {
        useWindow("../fxmlFiles/GameWindow.fxml", "Новая игра");
    }


    @FXML
    void returnStartWindow() {
        useWindow("../fxmlFiles/StartWindow.fxml", "Приложение \"Балда\"");
    }


    private void useWindow(String nameFXML, String nameTitle) {
        Stage stage = (Stage) pointWinner.getScene().getWindow();
        stage.close();
        Parent root1 = null;
        try {
            root1 = FXMLLoader.load(getClass().getResource(nameFXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(nameTitle);
        assert root1 != null;
        stage.setScene(new Scene(root1));
        stage.show();
    }


    void initData(int pointWin, String nameWin) {
        pointWinner.setText(pointWin + "");
        nameWinner.setText(nameWin);
        new WorkWithDB().addDataDB(pointWin, nameWin);
    }



}
