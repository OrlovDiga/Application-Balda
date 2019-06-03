package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class StartWindowController {

    @FXML
    private Button start;


    @FXML
    private void openGameWindow(ActionEvent event) throws IOException {
        useWindow("GameWindow.fxml", "Приложение \"Балда\"");
    }


    @FXML
    private void checkRecords(ActionEvent event) throws IOException {
        useWindow("RecordsWindow.fxml", "Приложение \"Балда\"");
    }


    @FXML
    private void checkOptonsList(ActionEvent event) throws IOException {
        useWindow("OptionsWindow.fxml", "Настройки");
    }


    @FXML
    private void exiting(ActionEvent event) {
        Platform.exit();
    }

    private void useWindow(String nameFXML, String nameTitle) throws IOException {
        Stage stage = (Stage) start.getScene().getWindow();
        stage.close();
        Parent root1 = FXMLLoader.load(getClass().getResource(nameFXML));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle(nameTitle);
        stage.setScene(new Scene(root1));
        stage.show();
    }

}
