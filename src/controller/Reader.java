package controller;

import model.Word;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
import java.util.Random;

public class Reader {
    public static final String path = "mots.txt";

    public Word getRandomWordAndDefinition() {
        Word word = null;
        int count = 0;

        try (BufferedReader lecteur = new BufferedReader(new FileReader(path))) {
            Random rand = new Random();
            String line;

            while ((line = lecteur.readLine()) != null) {
                count++;
                if (rand.nextInt(count) == 0) {
                    word = new Word(getWord(line), getDefinition(line));
                }
            }
        } catch (IOException e) {;}

        return word;
    }

    private String getWord(String line) {
        return line.split("\\s+")[0];
    }

    private String getDefinition(String line) {
        int firstSpaceIndex = line.indexOf(" ");
        if (firstSpaceIndex != -1 && firstSpaceIndex < line.length() - 1) {
            return line.substring(firstSpaceIndex + 1);
        }

        return "";

    }
}
