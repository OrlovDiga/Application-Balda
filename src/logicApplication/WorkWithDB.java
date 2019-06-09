package logicApplication;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class WorkWithDB {
    public void addDataDB(int pointWin, String nameWin) {
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
            System.err.println("Error: cant get database!");
        }

        try (Connection conn = DriverManager.getConnection(property.getProperty("db.host"),
                property.getProperty("db.login"),
                property.getProperty("db.password"))) {
            PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO baldarslt (name, point) VALUES (?, ?)");
            preparedStatement.setInt(2, pointWin);
            preparedStatement.setString(1, nameWin);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    void openRecords() {
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


    void temp() {

    }
}
