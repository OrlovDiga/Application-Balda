package logicApplication;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {

    public List<String> getAllWords() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("src/resources/AllWords.txt")));
        List<String> temp = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            temp.add(line.trim());
        }
        System.out.println(temp.get(1));
        return temp;
    }
}
