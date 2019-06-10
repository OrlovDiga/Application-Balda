package sample;

import javafx.animation.Animation;
        import javafx.animation.FadeTransition;
        import javafx.application.Application;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.layout.StackPane;
        import javafx.stage.Stage;
        import javafx.util.Duration;

public class Test extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Label label = new Label("Blink");
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1.0), label);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setCycleCount(Animation.INDEFINITE);
        fadeTransition.play();
        Scene scene = new Scene(new StackPane(label));
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
