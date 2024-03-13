package controller;

import model.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

/**
 * La classe Reader est responsable de la lecture des mots et de leurs définitions à partir d'un fichier texte.
 */
public class Reader {
    /**
     * Chemin du fichier contenant les mots et leurs définitions.
     */
    public static final String path = "mots.txt";

    /**
     * Récupère de manière aléatoire un mot et sa définition à partir du fichier spécifié.
     *
     * @return Un objet Word représentant le mot et sa définition lus à partir du fichier.
     */
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

    /**
     * Extrait le mot à partir d'une ligne du fichier.
     *
     * @param line La ligne du fichier contenant le mot et sa définition.
     * @return Le mot extrait de la ligne.
     */
    private String getWord(String line) {
        return line.split("\\s+")[0];
    }

    /**
     * Extrait la définition à partir d'une ligne du fichier.
     *
     * @param line La ligne du fichier contenant le mot et sa définition.
     * @return La définition extraite de la ligne.
     */
    private String getDefinition(String line) {
        int firstSpaceIndex = line.indexOf(" ");
        if (firstSpaceIndex != -1 && firstSpaceIndex < line.length() - 1) {
            return line.substring(firstSpaceIndex + 1);
        }

        return "";
    }
}
