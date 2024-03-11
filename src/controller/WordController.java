package controller;

import model.Word;
import view.PenduPanel;

import java.util.ArrayList;
import java.util.List;

public class WordController {
    private Word word;
    private PenduPanel penduPanel;
    private List<Character> guessedLetter;
    private List<Character> wrongLetters;

    public WordController(Word word, PenduPanel penduPanel) {
        this.word = word;
        this.penduPanel = penduPanel;
        this.guessedLetter = new ArrayList<>();
        this.wrongLetters = new ArrayList<>();
    }

    public boolean isLetterAlreadyGuessed(char lettreChar) {
        lettreChar = Character.toUpperCase(lettreChar);

        for (char c : guessedLetter) {
            if (Character.toUpperCase(c) == lettreChar) {
                return true;
            }
        }

        return false;
    }

    public void checkLetter(char lettreChar) {
        String wordStr = word.getWord();
        char[] wordArray = wordStr.toCharArray();
        lettreChar = Character.toUpperCase(lettreChar);
        boolean found = false;

        StringBuilder censoredWordBuilder = new StringBuilder(word.getCensoredWord());

        for (int i = 0; i < wordArray.length; i++) {
            if (Character.toUpperCase(wordArray[i]) == lettreChar) {
                censoredWordBuilder.setCharAt(i, wordArray[i]);
                found = true;
            }
        }

        if (! found) {
            penduPanel.increaseParts();
            wrongLetters.add(lettreChar);
        }

        word.setCensoredWord(censoredWordBuilder.toString());
        guessedLetter.add(lettreChar);
        System.out.println(word.getCensoredWord());

        if (word.getCensoredWord().equals(word.getWord())) {
            penduPanel.displayVictoryDialog();
        }
    }

    public List<Character> getWrongLetters() {
        return wrongLetters;
    }
}
