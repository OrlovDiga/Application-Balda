package sample;
//
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.sql.*;
//import java.util.Properties;
//
//public class Test {
//    public static void main(String[] args) throws IOException {
////        String url = "jdbc:mysql://localhost:3306/baldaresult?autoReconnect=true&useSSL=false";
////        String username = "root";
////        String pswd = "acecom839516T7";
////        Class.forName("com.mysql.jdbc.Driver");
////        try (Connection conn = DriverManager.getConnection(url, username, pswd);
////             Statement statement = conn.createStatement()) {
////            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS baldarslt (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, point INT(10) NOT NULL, PRIMARY KEY (id)) ");
////            statement.executeUpdate("INSERT INTO baldarslt (name, point) VALUES('INFERNO', 12)");
////            statement.executeUpdate("INSERT INTO baldarslt (name, point) VALUES('TEST', 13)");
////            /*ResultSet rs = statement.executeQuery("SELECT * FROM baldarslt ORDER BY point DESC ");
////            while (rs.next()) {
////                System.out.println(rs.getString(1));
////                System.out.println(rs.getString(2));
////                System.out.println(rs.getInt(3));
////                System.out.println("-------------------------");
////            }
////        } catch (SQLException e) {
////            e.printStackTrace();*/
//
//
////        }
//        FileInputStream in = new FileInputStream("/Users/macbook/untitled3/src/sample/config.properties");
//        Properties props = new Properties();
//        props.load(in);
//        in.close();
//
//        FileOutputStream out = new FileOutputStream("/Users/macbook/untitled3/src/sample/config.properties");
//        props.setProperty("country", "america");
//        props.store(out, null);
//        out.close();
//
//    }
//}
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
