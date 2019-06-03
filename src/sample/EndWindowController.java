package sample;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;


import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;
import java.util.ResourceBundle;

public class EndWindowController implements Initializable{
    @FXML
    Label pointWinner;
    @FXML
    Label nameWinner;

    private int pointWin;
    private String nameWin;


    public void setMainWindow(int pointWinner, String nameWinner){
        pointWin = pointWinner;
        nameWin = nameWinner;
    }


    @FXML
    void doNewGame() {
        useWindow("GameWindow.fxml", "Новая игра");
    }


    @FXML
    void returnStartWindow() {
        useWindow("StartWindow.fxml", "Приложение \"Балда\"");
    }


    private void useWindow(String nameFXML, String nameTitle){
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
        stage.setScene(new Scene(root1));
        stage.show();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        pointWinner.setText(pointWin + "");
        nameWinner.setText(nameWin);

        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("/Users/macbook/untitled3/src/sample/config.properties");
            property.load(fis);
        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("ОШИБКА: Проблема в подключение к базе данных!");
        }

        try (Connection conn = DriverManager.getConnection(property.getProperty("db.host"),
                                                           property.getProperty("db.login"),
                                                           property.getProperty("db.password"))) {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO baldarslt (name, point) VALUES (?, ?)");
            preparedStatement.setInt(2, pointWin);
            preparedStatement.setString(1, nameWin);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
