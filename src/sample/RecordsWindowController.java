package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
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
        Parent root1 = FXMLLoader.load(getClass().getResource("StartWindow.fxml"));
        stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Приложение");
        stage.setScene(new Scene(root1));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("/Users/macbook/untitled3/src/sample/config.properties");
            property.load(fis);
        } catch (IOException e) {
            System.err.println("Error: configuration file missing.");
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: Cant get database.");
        }

        try (Connection conn = DriverManager.getConnection(property.getProperty("db.host"),
                                                           property.getProperty("db.login"),
                                                           property.getProperty("db.password"));
             Statement statement = conn.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM baldarslt ORDER BY point DESC ");

            while (rs.next()) {
                recordsList.appendText(rs.getString(2) + " " + rs.getInt(3) + "\n");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
