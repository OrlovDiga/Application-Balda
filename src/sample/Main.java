package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;


public class Main extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../fxmlFiles/StartWindow.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        //Music for starting window /need correct!!!
        Media media = new Media("file://src/resources/MusicApplication.mp3");
        MediaPlayer player = new MediaPlayer(media);
        player.play();
    }


    public static void main(String[] args) {
        Application.launch(args);

    }
}
