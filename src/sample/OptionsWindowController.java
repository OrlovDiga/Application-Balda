package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;


public class OptionsWindowController {

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
            root1 = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Приложение");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    @FXML
    void changeTimePlay() {
        change(fieldTimePlay, "timePlay");
    }


    @FXML
    void changeNameFirst() {
        change(fieldNameFirst, "nameFirst");
    }


    @FXML
    void changeNameSecond() {
        change(fieldNameSec, "nameSecond");
    }

    void change(TextField who, String name) {
        String contentField = who.getText();

        if (!contentField.equals("")) {
            FileInputStream in = null;
            try {
                in = new FileInputStream("/Users/macbook/untitled3/src/sample/config.properties");
            } catch (FileNotFoundException e) {
                System.err.println("Error: configuration file missing.");
            }

            Properties props = new Properties();
            try {
                props.load(in);
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            FileOutputStream out = null;
            try {
                out = new FileOutputStream("/Users/macbook/untitled3/src/sample/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            props.setProperty(name, contentField);
            try {
                props.store(out, null);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
