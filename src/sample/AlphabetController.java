package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AlphabetController {

    private GameWindowController rootWindow;


    public void setMainWindow(GameWindowController rw){
        this.rootWindow = rw;
    }


    @FXML
    String useSymbol(ActionEvent event) {
        Button usingBut = (Button) event.getSource();
        this.rootWindow.getSymbol(usingBut.getText());
        Stage stage = (Stage) usingBut.getScene().getWindow();
        stage.close();
        return usingBut.getText();
    }
}