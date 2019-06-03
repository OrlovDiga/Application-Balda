package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.ResourceBundle;


import static java.lang.StrictMath.abs;

public class GameWindowController implements Initializable {

    Integer x = null;
    protected int quanityFreeButton = 20;
    Integer y = null;
    List<Pair<Integer, Integer>> wasXAndY = new ArrayList<>();
    List<String> allWords = new ArrayList<>();
    Button nowButton;
    int countTime;
    long lastUpdate = 0;
    private TimerStroke timer;
    int going = 1;//отвечает за то, чей сейчас ход
    TextArea nowWords;
    Label nowPoint;
    boolean flagWroteSymbol = false;
    Button newButtonSymbol;

    @FXML
    Button word;
    @FXML
    Label pointTwo;
    @FXML
    TextArea wordsTwo;
    @FXML
    TextArea wordsOne;
    @FXML
    Label pointOne;
    @FXML
    GridPane gamePanel;
    @FXML
    Label timeLabel;
    @FXML
    Button miss;
    @FXML
    Label personOne;
    @FXML
    Label personTwo;


    //пропустить ход
    @FXML
    void doMiss() {
        going++;
        timer.stop();
        timer = new TimerStroke(this);
        timer.start();
        newButtonSymbol = null;
        cancelSetSymbol();
    }


    //Если такое слово есть в словаре, то добавляем его и засчитваем очки за него.
    @FXML
    void addWord() throws IOException {
        if (allWords.contains(word.getText().trim().toLowerCase()) &&
           !wordsOne.getText().contains(word.getText()) &&
           !wordsTwo.getText().contains(word.getText())) {
             nowWords.setText(nowWords.getText() + "\n" + word.getText());
             nowPoint.setText(Integer.parseInt(nowPoint.getText()) + word.getText().length() + "");
             going++;
             timer.stop();
             timer = new TimerStroke(this);
             timer.start();
             newButtonSymbol = null;
             cancelSetSymbol();
             if (quanityFreeButton == 0) {
                 openEndWindow();
             }
       }
    }

    //вернуться на стартовое окно
    @FXML
    void returnStartWindow() {
        Stage stage = (Stage) word.getScene().getWindow();
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


    //Обработка игровой кнопки.
    @FXML
    synchronized void handleButton(ActionEvent event) throws IOException {
        nowButton = (Button) event.getSource();
        int nowX = GridPane.getRowIndex(nowButton);
        int nowY = GridPane.getColumnIndex(nowButton);
        if (nowButton.getText().equals("") && !flagWroteSymbol) {
            //Если нажали на пустую кнопку
            Stage stageAlphabet = new Stage();

            Stage stage1 = (Stage) word.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Alphabet.fxml"));
            GridPane rootAlphabet = loader.load();
            Scene windowAlphabet = new Scene(rootAlphabet, 600, 400);
            AlphabetController son = loader.getController();
            son.setMainWindow(this);

            stageAlphabet.setScene(windowAlphabet);
            stageAlphabet.initModality(Modality.WINDOW_MODAL);
            stageAlphabet.initOwner(stage1);
            stageAlphabet.show();

        } else if (flagWroteSymbol) {
            String color = "-fx-background-color: green";
            if (nowButton.getStyle().equals(color) && x == nowX && y == nowY) {

                //Для удаления отмеченной буквы
                int lastPair = wasXAndY.size() - 1; // координаты последней буквы
                x = wasXAndY.get(lastPair).getKey();
                y = wasXAndY.get(lastPair).getValue();
                wasXAndY.remove(lastPair);
                nowButton.setStyle(null);
                word.setText(word.getText().substring(0, word.getText().length() - 1));
            } else {
                if (!nowButton.getText().equals("") && !nowButton.getStyle().equals(color)) {
                    if (x == null && y == null) {
                        // Когда начинаем с новой буквы
                        wasXAndY.add(new Pair<>(x, y));
                        x = nowX;
                        y = nowY;
                        word.setText(nowButton.getText());
                        nowButton.setStyle(color);
                    } else if (abs((x + y) - (nowX + nowY)) == 1 ) {
                        //Продолжаем вводить слово(первая буква уже есть)
                        wasXAndY.add(new Pair<>(x, y));
                        x = nowX;
                        y = nowY;
                        word.setText(word.getText() + nowButton.getText());
                        nowButton.setStyle(color);
                    }
                }
            }
        }
    }


    //вставляем букву
    synchronized void getSymbol(String symbol) {
        nowButton.setText(symbol);
        quanityFreeButton--;
        newButtonSymbol = nowButton;
        flagWroteSymbol = true;
        new MediaPlayer(new Media("file:///Users/macbook/untitled3/MusicForWrite.mp3")).play();
    }


    //отмена всех действий
    @FXML
    void cancelSetSymbol() {
        for (int i = 0; i < 25; i++) {
            Button button = (Button) gamePanel.getChildren().get(i);
            button.setStyle(null);
        }
        word.setText(null);
        wasXAndY.clear();
        x = null;
        y = null;
        if (flagWroteSymbol) {
            quanityFreeButton++;
        }
        flagWroteSymbol = false;
        try {
            newButtonSymbol.setText("");
        } catch (NullPointerException e) {

        }

    }


    private void getAllWords() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("./AllWords.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            allWords.add(line.trim());
        }
    }

    //открытие завершающего окна(когда кто-то из участников выиграет)
    void openEndWindow() throws IOException {
        Stage endStage = new Stage();
        endStage.initModality(Modality.APPLICATION_MODAL);
        endStage.setTitle("Результаты");
        FXMLLoader loaderEnd = FXMLLoader.load(getClass().getResource("EndWindow.fxml"));
        GridPane rootEndWindow = loaderEnd.load();
        Scene windowEnd = new Scene(rootEndWindow, 600, 400);
        EndWindowController son = loaderEnd.getController();

        int point1 = Integer.parseInt(pointOne.getText());
        int point2 = Integer.parseInt(pointTwo.getText());
        if (point1 > point2) {
            son.setMainWindow(point1, "1");
        } else {
            son.setMainWindow(point2, "2");
        }

        endStage.setScene(windowEnd);
        endStage.show();

        Stage thisStage = (Stage) word.getScene().getWindow();
        thisStage.close();
        timer.stop();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream(new File("/Users/macbook/untitled3/src/sample/config.properties"));
            property.load(fis);
        } catch (IOException e) {
            System.err.println("Error: configuration file missing.");
        }

        personOne.setText(property.getProperty("nameFirst"));
        personTwo.setText(property.getProperty("nameSecond"));
        countTime = Integer.parseInt(property.getProperty("timePlay"));

        nowPoint = pointOne;
        nowWords = wordsOne;
        timer = new TimerStroke(this);
        timer.start();
        try {
            getAllWords();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}