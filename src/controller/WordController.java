package controller;

import model.Word;
import view.PenduPanel;

import java.util.ArrayList;
import java.util.List;

/**
 * Le contrôleur WordController gère la logique liée au mot deviné dans le jeu du pendu.
 */
public class WordController {
    private Word word;
    private PenduPanel penduPanel;
    private List<Character> guessedLetters;
    private List<Character> wrongLetters;

    /**
     * Constructeur de WordController.
     *
     * @param word       Le mot à deviner.
     * @param penduPanel Le panneau de pendu associé pour afficher les informations.
     */
    public WordController(Word word, PenduPanel penduPanel) {
        this.word = word;
        this.penduPanel = penduPanel;
        this.guessedLetters = new ArrayList<>();
        this.wrongLetters = new ArrayList<>();
    }

    /**
     * Vérifie si une lettre a déjà été devinée.
     *
     * @param letter La lettre à vérifier.
     * @return true si la lettre a déjà été devinée, sinon false.
     */
    public boolean isLetterAlreadyGuessed(char letter) {
        letter = Character.toUpperCase(letter);

        for (char c : guessedLetters) {
            if (Character.toUpperCase(c) == letter) {
                return true;
            }
        }

        return false;
    }

    /**
     * Vérifie si la lettre devinée est présente dans le mot à deviner.
     *
     * @param letter La lettre devinée.
     */
    public void checkLetter(char letter) {
        String wordStr = word.getWord();
        char[] wordArray = wordStr.toCharArray();
        letter = Character.toUpperCase(letter);
        boolean found = false;

        StringBuilder censoredWordBuilder = new StringBuilder(word.getCensoredWord());

        for (int i = 0; i < wordArray.length; i++) {
            if (Character.toUpperCase(wordArray[i]) == letter) {
                censoredWordBuilder.setCharAt(i, wordArray[i]);
                found = true;
            }
        }

        if (!found) {
            penduPanel.increaseParts();
            wrongLetters.add(letter);
        }

        word.setCensoredWord(censoredWordBuilder.toString());
        guessedLetters.add(letter);

        if (word.getCensoredWord().equals(word.getWord())) {
            penduPanel.displayVictoryDialog();
        }
    }

    /**
     * Récupère la liste des lettres devinées incorrectement.
     *
     * @return La liste des lettres devinées incorrectement.
     */
    public List<Character> getWrongLetters() {
        return wrongLetters;
    }
}
