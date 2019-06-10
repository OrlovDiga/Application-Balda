package logicApplication;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class WorkWithOptions {

    public void change(String contentField ,String name) {
        if (!contentField.equals("")) {
            FileInputStream in = null;
            try {
                in = new FileInputStream("src/resources/config.properties");
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
                out = new FileOutputStream("src/resources/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            props.setProperty(name, contentField);
            try {
                assert out != null;
                props.store(out, null);
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void changeTime(TextField who, String name) {
        String contentField = who.getText();

        if (contentField.length() > 10 && Integer.parseInt(contentField) >= 1000) {
            change(contentField, name);
        } else {
            openInformationWindow("Ошибка!", "Вы ввели некорректные данные, попробуйте еще раз");
        }
    }


    public void changeName(TextField who, String name) {
        String contentField = who.getText();

        if (contentField.length() < 10) {
            change(contentField, name);
        } else {
            openInformationWindow("Ошибка!", "Вы ввели некорректные данные, попробуйте снова");
        }
    }


    public void openInformationWindow(String warning, String info) {
        Alert infWndw = new Alert(Alert.AlertType.INFORMATION);

        infWndw.setTitle(warning);
        infWndw.setHeaderText(null);
        infWndw.setContentText(info);

        infWndw.showAndWait();
    }
}
