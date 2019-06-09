package logicApplication;

import java.util.ArrayList;
import java.util.List;

public class SearchingWords {
    String firstWord = "¡¿Àƒ¿";
    private static final char EMPTY = 'x';
    List nowWords = new ArrayList();
    char[][] symbols = new char[6][6];

    void fillCard() {
        for (int i = 0; i < symbols.length - 1; i++) {
            for (int j = 0; j < symbols[i].length - 1; j++) {
                symbols[i][j] = EMPTY;
            }
        }
    }

    void setFirstSymbol() {
        char[] firstChar = firstWord.toCharArray();
        System.out.println(symbols.length);
        for (int i = 0; i < symbols[2].length - 1; i++) {
            symbols[2][i] = firstChar[i];
        }
    }


    void setSymbol(int x, int y, char sym) {
        symbols[x][y] = sym;
    }


    void search() {
        for (int i = 1; i < symbols.length; i++) {
            for (int j = 1; j < symbols[i].length; j++) {
                if (symbols[i][j] == EMPTY) {
                    if (symbols[i + 1][j] != EMPTY) {

                    }
                    if (symbols[i][j + 1] != EMPTY) {

                    }
                }
            }
        }
    }


    void test() {
        for (int i = 0; i < symbols.length; i++) {
            for (int j = 0; j < symbols[i].length; j++) {
                System.out.print(symbols[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SearchingWords k = new SearchingWords();
        k.setFirstSymbol();
        k.test();
    }


}
