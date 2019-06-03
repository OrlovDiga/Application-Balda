package sample;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException {
//        String url = "jdbc:mysql://localhost:3306/baldaresult?autoReconnect=true&useSSL=false";
//        String username = "root";
//        String pswd = "acecom839516T7";
//        Class.forName("com.mysql.jdbc.Driver");
//        try (Connection conn = DriverManager.getConnection(url, username, pswd);
//             Statement statement = conn.createStatement()) {
//            //statement.executeUpdate("CREATE TABLE IF NOT EXISTS baldarslt (id MEDIUMINT NOT NULL AUTO_INCREMENT, name CHAR(30) NOT NULL, point INT(10) NOT NULL, PRIMARY KEY (id)) ");
//            statement.executeUpdate("INSERT INTO baldarslt (name, point) VALUES('INFERNO', 12)");
//            statement.executeUpdate("INSERT INTO baldarslt (name, point) VALUES('TEST', 13)");
//            /*ResultSet rs = statement.executeQuery("SELECT * FROM baldarslt ORDER BY point DESC ");
//            while (rs.next()) {
//                System.out.println(rs.getString(1));
//                System.out.println(rs.getString(2));
//                System.out.println(rs.getInt(3));
//                System.out.println("-------------------------");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();*/


//        }
        FileInputStream in = new FileInputStream("/Users/macbook/untitled3/src/sample/config.properties");
        Properties props = new Properties();
        props.load(in);
        in.close();

        FileOutputStream out = new FileOutputStream("/Users/macbook/untitled3/src/sample/config.properties");
        props.setProperty("country", "america");
        props.store(out, null);
        out.close();

    }
}
